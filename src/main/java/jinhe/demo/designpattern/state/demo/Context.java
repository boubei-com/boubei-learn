package jinhe.lt.designpattern.state.demo;

/**
 * <p>
 * Context.java
 * </p>
 * 
 * @author Jon.King  2006-4-22
 * 
 */
public class Context {

    private State state = null; // 我们将原来的 Color state 改成了新建的State state;

    // setState是用来改变state的状态 使用setState实现状态的切换
    public void setState(State state) {
        this.state = state;
    }

    public void push() {
        // 状态的切换的细节部分,在本例中是颜色的变化,已经封装在子类的handlepush中实现,这里无需关心
        state.handlePush(this);

        // 因为sample要使用state中的一个切换结果,使用getColor()
        // Sample sample=new Sample(state.getColor());
        // sample.operate();
    }

    public void pull() {
        state.handlePull(this);
        // Sample2 sample2=new Sample2(state.getColor());
        // sample2.operate();
    }

}

//上面是对下面代码的改造

/*
 *public class Context{

  　　private Color state=null;

  　　public void push(){

  　　　　//如果当前red状态 就切换到blue
  　　　　if (state==Color.red) state=Color.blue;

  　　　　//如果当前blue状态 就切换到green
  　　　　else if (state==Color.blue) state=Color.green;

  　　　　//如果当前green状态 就切换到red
  　　　　else if (state==Color.black) state=Color.red;
  　　　　
  　　　　Sample sample=new Sample(state);
  　　　　sample.operate();
  　　}

  　　public void pull(){

  　　　　//与push状态切换正好相反

  　　　　if (state==Color.green) state=Color.blue;
  　　　　else if (state==Color.blue) state=Color.red;
  　　　　else if (state==Color.red) state=Color.green;

  　　　　Sample2 sample2=new Sample2(state);
  　　　　sample2.operate(); 
  　　}
  }
*/
