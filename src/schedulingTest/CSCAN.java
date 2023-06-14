package schedulingTest;

public class CSCAN {

	public static boolean isCloser(int currentPos, int nextPos1, int nextPos2) {
		int dif1;
		int dif2;
		dif1 = nextPos1-currentPos;
		dif2 = nextPos2-currentPos;
		if(dif1<0) {
			dif1+=100;
		}
		if(dif2<0) {
			dif2+=100;
		}
		return dif1 < dif2;
	}

	public static int[][] arrangeArrival(int[][] items) {
		int currentPos;
		int[][] arranged = items.clone();
		int[] temp = new int[2];
		int minST;
		for (int i = 0; i < arranged.length - 1; i++) {
			currentPos = arranged[i][1];
			minST=i+1;			
			for (int j = i+1; j < arranged.length; j++) {
				if (isCloser(currentPos, arranged[j][1], arranged[minST][1])) {
					minST=j;
				}
			}
			temp=arranged[i+1];
			arranged[i+1]=arranged[minST];
			arranged[minST]=temp;
		}
		return arranged;
	}
}
