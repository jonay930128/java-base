package wrx.xing.data_structures.arr;

/**
 * 请填写类的描述
 * 二维数组转稀疏数组，可以得到压缩效果
 * 稀疏数组再转回原数组
 * <p>
 * 稀疏数组：
 * n行3列的二维数组
 * 第一行第一列：原数组的行
 * 第一行第二列：原数组的列
 * 第一行第三列：原数组非空值的个数也即稀疏数组除去第一行后剩下的行数。
 * 其他的行的存储情况为：第一列存原行，第二列存原列，第二列存原数值
 *
 * @author wangruxing
 * @date 2019-09-28 14:04
 */
public class ArrToSparse {

	public static void main(String[] args) {
		int[][] arr = initArr();
		System.out.println("原始数组为：");
		printArr(arr);
		int[][] sparseArr = arrToSparse(arr);
		System.out.println("转为稀疏数组为：");
		printArr(sparseArr);
		int [][] arr2 = sparseToArr(sparseArr);
		System.out.println("稀疏数组转回原二维数组：");
		printArr(arr2);
	}

	/**
	 * 稀疏数组转二维数组
	 * @param sparseArr 稀疏数组
	 * @return 二维数组
	 */
	private static int[][] sparseToArr(int[][] sparseArr) {
		int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];
		for (int i = 1; i < sparseArr.length; i++) {
			arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		return arr;
	}

	/**
	 * 数组的打印方法
	 * @param arr 二维数组
	 */
	private static void printArr(int[][] arr) {
		for (int[] ints : arr) {
			for (int data : ints) {
				System.out.printf("%d\t",data);
			}
			System.out.println();
		}
	}

	/**
	 * 二维数组转稀疏数组
	 * @param arr 二维数组
	 * @return 返回一个稀疏数组
	 */
	private static int[][] arrToSparse(int[][] arr) {
		// 列
		int row = arr[0].length;
		// 行
		int col = 0;
		int valCount = 0;
		for (int[] ints : arr) {
			col++;
			for (int i : ints) {
				if (0 != i) {
					valCount++;
				}
			}
		}
		int[][] sparseArr = new int[valCount + 1][3];
		sparseArr[0][0] = col;
		sparseArr[0][1] = row;
		sparseArr[0][2] = valCount;

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = arr[i][j];
				}
			}
		}

		return sparseArr;
	}

	/**
	 * 初始化一个二维数组
	 *
	 * @return
	 */
	private static int[][] initArr() {
		int[][] arr = new int[9][11];
		arr[2][2] = 3;
		arr[6][4] = 4;
		arr[8][9] = 5;
		return arr;
	}
}
