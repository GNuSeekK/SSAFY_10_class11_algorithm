// Outer class

import java.io.*;
import java.util.*;

public class Main {
	static int[][] seat;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N = 0;
	static int result = 0;

	public static void checkPeople(int[] arr, int studentNumber) {

		int max = 0;
		int count = 0;
		int blank_count = 0;
		int blank_count_max = 0;
		int index_i = N;
		int index_j = N;
		for (int i = 0; i < seat.length; i++) {
			for (int j = 0; j < seat[i].length; j++) {

				if (seat[i][j] == 0) { // 현재자리가 비어있는 곳을 탐색
					for (int p = 0; p < 4; p++) {
						if (i + dx[p] >= 0 && i + dx[p] < N && j + dy[p] >= 0 && j + dy[p] < N) {

							for (int temp = 0; temp < 4; temp++) {
								if (arr[temp] == seat[i + dx[p]][j + dy[p]]) { // 근접위치에 좋아하는 사람 카운트
									count++;
									break;
								}
							}
							if (seat[i + dx[p]][j + dy[p]] == 0) {// 근접위치 빈칸 카운트
								blank_count++;
							}
						}
					}

					if (max < count) {// 주변에 좋아하는 사람 수가 높으면 교체
						index_i = i;
						index_j = j;
						max = count;

					} else if (max == count) { // 주변에 좋아하는 사람수가 같다면
//						System.out.println(blank_count + " " + blank_count_max);
						if (blank_count_max < blank_count) { // 인접 빈칸이 많다면 교체

							index_i = i;
							index_j = j;
							blank_count_max = blank_count;

						} else if (blank_count_max == blank_count) { // 인접 빈칸이 같다면

							if (index_i > i) {
								index_i = i;
								index_j = j;
							} else if (index_i == i) {
								if (index_j > j) {
									index_j = j;
								}
							}
						}
					}

					count = 0;
					blank_count = 0;
				}

			}
		}

		seat[index_i][index_j] = studentNumber;
//		for(int i =0; i < N; i++) {
//			System.out.println(Arrays.toString(seat[i]));
//		}
//		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[][] arr = new int[(int) Math.pow(N, 2)][5];
		for (int student = 0; student < Math.pow(N, 2); student++) {
			arr[student] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		seat = new int[N][N];

		for (int i = 0; i < arr.length; i++) {
			int studentNumber = arr[i][0];

			checkPeople(Arrays.copyOfRange(arr[i], 1, 5), studentNumber);
		}

		for (int i = 0; i < seat.length; i++) {
			for (int j = 0; j < seat.length; j++) {
				int count = 0;
				for (int q = 0; q < Math.pow(N, 2); q++) {
					if (arr[q][0] == seat[i][j]) {
						for (int p = 0; p < 4; p++) {// 사방탐색
							if (i + dx[p] >= 0 && i + dx[p] < N & j + dy[p] >= 0 && j + dy[p] < N) { // 인덱스 조정
								for (int k = 1; k <= 4; k++) {
									if(arr[q][k] == seat[i+dx[p]][j+dy[p]]) {
										count++;
										break;
									}
									
								}
							}
						}
						break;

					}
					
					
				}
				if(count == 1) {
					result += 1;
				}else if(count == 2) {
					result += 10;
				}else if(count == 3) {
					result += 100;
				}else if(count == 4) {
					result += 1000;
				}

			}
		}
		
		
		System.out.println(result);

	}
}
