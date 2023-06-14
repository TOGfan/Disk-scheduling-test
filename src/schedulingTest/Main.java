package schedulingTest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static int[] findDiskHeadDistance(int items[][]) {
		int[] wt = new int[items.length];
		wt[0]=0;
		int diff=0;
		for (int i = 1; i < items.length; i++) {
			diff=items[i-1][1]-items[i][1];
			if(diff<0)diff*=-1;
			wt[i] = diff;
		}
		return wt;
	}
	public static int missedItems(int wt[],int[][] items) {
		int missed=0;
		int time=0;
		for(int i=0;i<items.length;i++) {
			time+=wt[i];
			if(time>items[i][2]) {
				missed++;
			}
		}
		return missed;
	}
	public static float findAvgDiskHeadDistance(int wt[]) {
		int total_wt = 0;
		
		for (int i = 0; i < wt.length; i++) {
			total_wt = total_wt + wt[i];
		}
		return (float) total_wt / wt.length;
	}
	public static float findAvgDiskHeadDistanceDeadline(int wt[], int items[][]) {
		int total_wt = 0;
		int n=items.length;
		for (int i = 0; i < wt.length; i++) {
			
			total_wt = total_wt + wt[i];
			if(total_wt>items[i][2]) {
				total_wt-=wt[i];
				if(i<wt.length-1)wt[i+1]+=wt[i];
				n--;
			}
		}
		return (float) total_wt / n;
	}
	public static void printItems(int items[][]) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[i].length; j++) {
				System.out.print(items[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void saveItems(int items[][], PrintWriter writer) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[i].length; j++) {
				writer.print(items[i][j] + " ");
			}
			writer.println();
		}
	}

	public static int[][] generateRandomItems(int n) {
		Random random = new Random();
		int[][] processes = new int[n][2];
		for (int i = 0; i < n; i++) {
			processes[i][0] = i + 1;
			processes[i][1] = random.nextInt() % 51 + 50;
		}
		return processes;
	}
	public static int[][] generateDeadlines(int n,int items[][]) {
		Random random = new Random();
		int[][] deadlines = new int[n][3];
		for(int i=0;i<n;i++) {
			deadlines[i][0]=items[i][0];
			deadlines[i][1]=items[i][1];
			deadlines[i][2]=random.nextInt() % (n/2+51) + n/2+50;
		}
		return deadlines;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of processes:");
		int n = scanner.nextInt();
		scanner.close();
		int[][] items = generateRandomItems(n);
		int[][] FCFSarr = FCFS.arrangeArrival(items);
		int[][] SSTFarr = SSTF.arrangeArrival(items);
		int[][] SCANarr = SCAN.arrangeArrival(items);
		int[][] CSCANarr = CSCAN.arrangeArrival(items);
		items = generateDeadlines(n,items);
		int[][] EDFarr = EDF.arrangeArrival(items);
		int[][] FDSCANarr = FDSCAN.arrangeArrival(items);
		System.out.println("Items: (ID, position on the disk, deadline):\n");
		printItems(items);	
		System.out.println("Item queue aranged by FCFS:");
		printItems(FCFSarr);
		System.out.println("Item queue aranged by SSTF:");
		printItems(SSTFarr);
		System.out.println("Item queue aranged by SCAN:");
		printItems(SCANarr);
		System.out.println("Item queue aranged by CSCAN:");
		printItems(CSCANarr);
		System.out.println("Item queue aranged by EDF:");
		printItems(EDFarr);
		System.out.println("Item queue aranged by FDSCAN:");
		printItems(FDSCANarr);		
		System.out.println("Average disk head movement of FCFS: " + findAvgDiskHeadDistance(findDiskHeadDistance(FCFSarr)));
		System.out.println("Average disk head movement of SSTF: " + findAvgDiskHeadDistance(findDiskHeadDistance(SSTFarr)));
		System.out.println("Average disk head movement of SCAN: " + findAvgDiskHeadDistance(findDiskHeadDistance(SCANarr)));
		System.out.println("Average disk head movement of CSCAN: " + findAvgDiskHeadDistance(findDiskHeadDistance(CSCANarr)));
		System.out.println("Average disk head movement of EDF: " + findAvgDiskHeadDistance(findDiskHeadDistance(EDFarr)));
		System.out.println("Average disk head movement of FDSCAN: " + findAvgDiskHeadDistance(findDiskHeadDistance(FDSCANarr)));
		System.out.println("Number of items missed by EDF: " +missedItems(findDiskHeadDistance(FDSCANarr),EDFarr));
		System.out.println("Number of items missed by FDSCAN: " +missedItems(findDiskHeadDistance(FDSCANarr),FDSCANarr));
		try {
			PrintWriter results = new PrintWriter("Results.txt");
			results.println("Items: (ID, position on the disk, deadline):\n");
			saveItems(items,results);	
			results.println("Item queue aranged by FCFS:");
			saveItems(FCFSarr,results);
			results.println("Item queue aranged by SSTF:");
			saveItems(SSTFarr,results);
			results.println("Item queue aranged by SCAN:");
			saveItems(SCANarr,results);
			results.println("Item queue aranged by CSCAN:");
			saveItems(CSCANarr,results);
			results.println("Item queue aranged by EDF:");
			saveItems(EDFarr,results);
			results.println("Item queue aranged by FDSCAN:");
			saveItems(FDSCANarr,results);		
			results.println("Average disk head movement of FCFS: " + findAvgDiskHeadDistance(findDiskHeadDistance(FCFSarr)));
			results.println("Average disk head movement of SSTF: " + findAvgDiskHeadDistance(findDiskHeadDistance(SSTFarr)));
			results.println("Average disk head movement of SCAN: " + findAvgDiskHeadDistance(findDiskHeadDistance(SCANarr)));
			results.println("Average disk head movement of CSCAN: " + findAvgDiskHeadDistance(findDiskHeadDistance(CSCANarr)));
			results.println("Average disk head movement of EDF: " + findAvgDiskHeadDistance(findDiskHeadDistance(EDFarr)));
			results.println("Average disk head movement of FDSCAN: " + findAvgDiskHeadDistance(findDiskHeadDistance(FDSCANarr)));
			results.println("Number of items missed by EDF: " +missedItems(findDiskHeadDistance(FDSCANarr),EDFarr));
			results.println("Number of items missed by FDSCAN: " +missedItems(findDiskHeadDistance(FDSCANarr),FDSCANarr));
			results.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
