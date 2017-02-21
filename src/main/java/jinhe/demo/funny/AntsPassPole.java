package jinhe.lt.funny;

/**
 * 有一根300厘米的细木杆，
 * 在第30厘米、80厘米、110厘米、160厘米、250厘米这五个位置上各有一只蚂蚁。
 * 木杆很细，不能同时通过两只蚂蚁。
 * 
 * 开始时，蚂蚁的头朝左还是朝右是任意的，它们只会朝前走或调头，但不会后退。
 * 当任意两只蚂蚁碰头时，两只蚂蚁会同时调头朝相反方向走。假设蚂蚁们每秒钟可以走5厘米的距离。
 * 请编写一个程序，计算各种可能情形下所有蚂蚁都离开木杆的最小时间和最大时间。
 * @author jinpj
 *
 */
class Ant {
	int direction; // -1:left 1:right
	int position; 
	int speed = 5; // defatlt: 5cm/s
	
	int distance = 0;
	
	Ant(int direction, int position) {
		this.direction = direction;
		this.position = position;
	}
	
	public void move() { 
		this.position = this.position + this.speed * this.direction;
		distance += this.speed;
	}
	
	public String toString(){
		return "(" + direction + ", " + position + ", " + distance + ")  ";
	}
}
	
public class AntsPassPole {
	
	private boolean showLog = false;

	int directions[] = {-1, 1};
	int positions[] = {30, 80, 110, 160, 250};
	
	Ant[] ants = new Ant[5];
	int clock = 0 ;
	int totalDistance = 0;
		
	public AntsPassPole(char[] direcetions){		
		for(int i = 0; i < positions.length; i++) {
			ants[i] = new Ant(direcetions[i] == '1' ? 1 : -1, positions[i]);
		}
	}
	
	void dsiplay(){
		if(!showLog)
			return;
		
		for(int i = 0; i < ants.length; i++){
			System.out.print(ants[i]);
		}
		System.out.println();
	}
	
	public void start() {
		while(!scan()) { 
			clock++;
		}
		dsiplay();
		
		int totalDistance = 0;
		for(int i = 0; i < ants.length; i++){
			totalDistance += ants[i].distance;
		}
		System.out.println("用时 ：" + ++clock + "，总爬行距离：" + totalDistance);
	}
	
	boolean scan() {
		dsiplay();
		
		boolean isOver = true;
		for(int i = 0; i < ants.length; i++){
			Ant a = ants[i];
			if(i < ants.length - 1){
				Ant b = ants[i + 1];
				checkPosition(a, b);
			}
			if(a.position > 0 && a.position < 300) {
				a.move();
			}
			isOver = isOver && (a.position == 0 || a.position == 300);
		}
		return isOver;
	}
	
	void checkPosition(Ant a, Ant b) {
		if(a.position == b.position){
			a.direction = a.direction * -1;
			b.direction = b.direction * -1;
		}
	}
	
	public static void main(String[] args) {
		// 2的5次  00000 --> 11111  0 --> 31
		
		for(int i = 0; i < 32; i++) {
			String binaryStr = Integer.toBinaryString(i);
			
			char[] temp = {'0', '0', '0', '0', '0'}; 
			
			char[] charArray = binaryStr.toCharArray();
			for(int j = charArray.length - 1; j >= 0; j--){
				temp[4 - j] = charArray[charArray.length - j - 1];
			}
			System.out.print(i + "_" + new String(temp));
			
			new AntsPassPole(temp).start();
		}
	}
}
