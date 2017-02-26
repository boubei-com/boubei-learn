package com.boubei.learn.jk.database.connpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boubei.learn.jk.BeanUtil;

/** 
 * <p> PoolManager.java </p> 
 * 
 * @author Jon.King 2006-4-19
 * 
 * 连接池管理类,PoolManager为包级私有，包外对象将无法访问，只能由代理类PoolManagerProxy来代理其功能
 * 
 */
class PoolManager {
    private List<PoolItem> poolItems = new ArrayList<PoolItem>();  //连接池，存放多个PoolItem对象
    private final int POOL_MAX_SIZE = 100;                         //连接数上限为100
    
    /**
     * 内部静态类，可以在没有enclosing class（外围类）的情况下单独存在，如Map中的Entry
     * 此处一个PoolItem对象包含一个Connetion连接和其使用状态isUse。
     */
    private static class PoolItem {
        boolean inUse;  //设置该属性是用以保证每个用户只能单独使用一个连接（如此同步才不会出错），inUse=true的一律不可用
        ConnectionLocator connLocator; //ConnectionLocator是对Connection的本地封装类，包含get，set，release功能

        PoolItem(ConnectionLocator conn) {
            this.connLocator = conn;
            this.inUse = false;
        }
    }
    /**
     * 进一步封装上面的PoolItem类，新增一个released属性，用以判断当前PoolItem实例是否已经被释放掉
     * ReleasablePoolItem具备了释放当前连接的功能
     *
     */
    public class ReleasablePoolItem {
        private PoolItem poolItem;
        private boolean released;

        public ReleasablePoolItem(PoolItem poolItem) {
            this.poolItem = poolItem;
            this.released  = false;
        }

        public ConnectionLocator getConnectionLocator() {
            if (released) {
                throw new RuntimeException("试图连接一个已经释放掉的连接，连接失败！");
            }
            return poolItem.connLocator;
        }

        /**
         * 将使用完毕的数据库连接设置为可用状态（PoolItem.inUse = false），则其他用户可使用该连接。
         * 判断当前池中连接数是否已经超过阀值（POOL_MAX_SIZE）， 如果超过，则关闭该连接。 否则设置其使用状态为可用以备下次重用。
         */
        public void release() {
            if(poolItems.size() > POOL_MAX_SIZE){
                released = true;
                poolItem.inUse = false;
                poolItem.connLocator.releaseConnection();  //先关闭ConnectionLocator中的conn连接
                poolItem.connLocator  = null;
                poolItems.remove(poolItem);    //将该连接对象从连接池中移掉
            } 
            else {
                poolItem.inUse = false;
            }
        }
    }

    /**
     * 从连接池中获取一个未被使用（inUse=false）的连接对象poolItem,封装成ReleasablePoolItem对象返回。
     * ReleasablePoolItem对象具备释放连接的功能。
     * 
     * 如果当前池中有可用连接，则将池中最后一个返回，如果没有，则在不超出POOL_MAX_SIZE的前提下新建一个返回
     * 
     * @return
     *        ReleasablePoolItem
     */
    public ReleasablePoolItem get() {
        PoolItem poolItem = null;
        for (Iterator<PoolItem> it = poolItems.iterator(); it.hasNext(); ) {
            poolItem = it.next();
            if (poolItem.inUse == false) {
                poolItem.inUse = true;    //在用户获取连接的时候将该连接状态设置为“使用中”，inUse=true;
                return new ReleasablePoolItem(poolItem);
            }
        }
        //取不到则新建一个新建一个ConnectionLocator，里面包含一个Connection实例，封装成PoolItem加入到池poolItems中
        if(poolItems.size() < POOL_MAX_SIZE){
            addConnectionLocator();
            poolItem = (PoolItem) poolItems.get(poolItems.size() - 1); //获取最后一个PoolItem，即刚新建的
            poolItem.inUse = true;    //addConnectionLocator()新建出来的PoolItem实例使用状态为false，在被用户取出前应先设置为已使用
            return new ReleasablePoolItem(poolItem);
        } else {            
            throw new RuntimeException("连接池已满，当前无可用连接，也无法创建新的连接!");
        }       
    }
    
    /**
     * 新增一个连接connLocator，封装成使用状态为未使用（inUse=false）的PoolItem对象加入连接池中
     */
    private static String CONNECTION_IMPL_NAME = DBConnectionLocator.class.getName();
    
    private void addConnectionLocator() {
        ConnectionLocator connLocator = (ConnectionLocator) BeanUtil.newInstanceByName(CONNECTION_IMPL_NAME);
        poolItems.add(new PoolItem(connLocator));
    }
}
