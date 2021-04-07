import java.io.*;
import java.util.*;

public class ShellRotate {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] arr = new int[n][m];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		int s = sc.nextInt();
		int r = sc.nextInt();

		rotateShell(arr, s, r);
		display(arr);

	}

	public static void rotateShell(int[][] arr, int s, int r) {
		int[] oneDShell = fillOneDFromShell(arr, s);
		rotate(oneDShell, r);
		fillShellFromOneD(arr, s, oneDShell);

	}

	public static int[] fillOneDFromShell(int[][] arr, int s) {
		int minr = s - 1;
		int minc = s - 1;
		int maxr = arr.length - s;
		int maxc = arr[0].length - s;
		// int size = left_wall + bottom_wall + right_wall + top_wall - 4
		// = 2 * left wall + 2* bottom wall
		// int size = 2*(maxr-minr +1) + 2*(maxc-minc+1) -4;
		int size = 2 * (maxr - minr + maxc - minc);

		int[] oneD = new int[size];
		int index = 0;
		// left wall
		for (int i = minr, j = minc; i <= maxr; i++) {
			oneD[index] = arr[i][j];
			index++;
		}

		// bottom wall
		for (int i = maxr, j = minc + 1; j <= maxc; j++) {
			oneD[index] = arr[i][j];
			index++;
		}

		// right wall
		for (int i = maxr - 1, j = maxc; i >= minr; i--) {
			oneD[index] = arr[i][j];
			index++;
		}

		// top wall
		for (int i = minr, j = maxc - 1; j >= minc + 1; j--) {
			oneD[index] = arr[i][j];
			index++;
		}

		return oneD;
	}

	public static void fillShellFromOneD(int[][] arr, int s, int[] oneD) {
		int minr = s - 1;
		int minc = s - 1;
		int maxr = arr.length - s;
		int maxc = arr[0].length - s;

		int index = 0;
		// left wall
		for (int i = minr, j = minc; i <= maxr; i++) {
			arr[i][j] = oneD[index];
			index++;
		}

		// bottom wall
		for (int i = maxr, j = minc + 1; j <= maxc; j++) {
			arr[i][j] = oneD[index];
			index++;
		}

		// right wall
		for (int i = maxr - 1, j = maxc; i >= minr; i--) {
			arr[i][j] = oneD[index];
			index++;
		}

		// top wall
		for (int i = minr, j = maxc - 1; j >= minc + 1; j--) {
			arr[i][j] = oneD[index];
			index++;
		}

	}

	public static void rotate(int[] oneD, int r) {
		r = r % oneD.length;
		if (r < 0) {
			r = r + oneD.length;
		}

		reverse(oneD, 0, oneD.length - r - 1);
		reverse(oneD, oneD.length - r, oneD.length - 1);
		reverse(oneD, 0, oneD.length - 1);
	}

	public static void reverse(int[] arr, int li, int ri) {
		while (li < ri) {
			int temp = arr[li];
			arr[li] = arr[ri];
			arr[ri] = temp;

			li++;
			ri--;
		}
	}

	public static void display(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}