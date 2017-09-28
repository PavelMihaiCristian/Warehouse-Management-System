package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class partition {

	private static int partitionQuick(int[] A, int p, int r) {

		int x = A[p];// pivot
		int i = p - 1;
		int j = r + 1;

		while (true) {
			do {
				j--;
			} while (A[j] > x);
			do {
				i++;
			} while (A[i] < x);
			if (i < j)
				swap(A, i, j);
			else
				return j;
		}
	}

	private static void swap(int[] A, int i, int j) {
		// TODO Auto-generated method stub
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	public static void QuickSort(int A[], int p, int r) {

		if (p < r) {
			int q = partitionQuick(A, p, r);
			QuickSort(A, p, q);
			QuickSort(A, q + 1, r);
		}
	}
	
	public static boolean isOrdered(int[] A){
		if(A.length==1) return true;
		else {
			for(int i=1;i<A.length;i++){
				if(A[i]<A[i-1])return false;
			}
			return true;
		}
	}
	
	public static boolean isPermutation(int[] A,int[] B){
		if(A.length!=B.length) return false;
		Arrays.sort(A);
		Arrays.sort(B);
		for(int i=0;i<A.length;i++){
			if(A[i]!=B[i]) return false;
		}
		return true;
	}

	private static <T extends Comparable<T>> int partitionQuickGeneric(T[] A, int p, int r) {

		T x = A[p];// pivot
		int i = p - 1;
		int j = r + 1;

		while (true) {
			do {
				j--;
			} while (A[j].compareTo(x)>0);
			do {
				i++;
			} while (A[i].compareTo(x)<0);
			if (i < j)
				swapGeneric(A, i, j);
			else
				return j;
		}
	}
	
	private static <T extends Comparable<T>> void swapGeneric(T[] A,int i,int j){
		T temp=A[i];
		A[i]=A[j];
		A[j]=temp;
	}
	
	public static <T extends Comparable<T>> void QuickSortGeneric(T[] A, int p, int r) {

		if (p < r) {
			int q = partitionQuickGeneric(A, p, r);
			QuickSortGeneric(A, p, q);
			QuickSortGeneric(A, q + 1, r);
		}
	}
	
	public static<T extends Comparable<T>> boolean isOrderedGeneric(T[] A){
		if(A.length==1) return true;
		else {
			for(int i=1;i<A.length;i++){
				if(A[i].compareTo(A[i-1])<0)return false;
			}
			return true;
		}
	}
	
	public static<T extends Comparable<T>> boolean isPermutationGeneric(T[] A,T[] B){
		if(A.length!=B.length) return false;
		Arrays.sort(A);;
		Arrays.sort(B);
		for(int i=0;i<A.length;i++){
			if(A[i].compareTo(B[i])!=0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		int[] A = { 3, 2, 1, 9, 5, 8, 4, 19, 14, 12, 18 };
		partition.QuickSort(A, 0, A.length - 1);
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		
	}
}
