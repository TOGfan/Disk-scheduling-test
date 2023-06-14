package schedulingTest;

public class FDSCAN {
	public static int[][] arrangeArrival(int[][] items) {
		boolean goingRight=true;
		int currentPos;
		int dif1;
		int dif2;
		int time=0;
		int[][] arranged = items.clone();
		int[] temp = new int[2];
		int minST;
		for (int i = 0; i < arranged.length - 1; i++) {
			currentPos = arranged[i][1];
			minST=i+1;	
			if(goingRight) {
				dif2=arranged[minST][1]-currentPos;
			}else {
				dif2 = currentPos-arranged[minST][1];
			}
			for (int j = i+1; j < arranged.length; j++) {
				if(goingRight) {
					dif1 = arranged[j][1]-currentPos;
				}else {
					dif1 = currentPos-arranged[j][1];
				}
				if((dif1<dif2&&dif1>=0||dif2<0)&&arranged[j][2]>time+dif1) {
					minST=j;
					if(goingRight) {
						dif2=arranged[minST][1]-currentPos;
					}else {
						dif2 = currentPos-arranged[minST][1];
					}
				}
			}
			if (dif2<0) {
				goingRight=!goingRight;
				i--;
				continue;
			}
			time+=dif2;
			temp=arranged[i+1];
			arranged[i+1]=arranged[minST];
			arranged[minST]=temp;
		}
		return arranged;
	}
}
