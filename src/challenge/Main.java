package challenge;

import java.io.IOException;

public class Main {
	static final int MAX_JSONARRAY = 1400;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = 100, k = 0, N = 10, sum = 0, target;
		double money = 29.99;
		target = (int) (money * 100);
		Data mydata = new Data();
		int current[] = new int[N];
		int ans[] = new int[N];
		int price[] = new int[n];
		for (int i = 0; i < N; i++)
			current[i] = -1;
		JSONArray data[] = new JSONArray[MAX_JSONARRAY];
		data[0] = mydata.getData();
		for (int i = 0; i < n; i++)
			price[i] = (int) (Double.parseDouble(data[i / 100]
					.getJSONObject(i % 100).getString("price").substring(1)) * 100);
		for (int i = n - 1; i >= n - N; i--) {
			sum += price[i];
			current[i - n + N] = i;
		}
		if (sum <= target) {
			for (int i = n - N; i < n; i++)
				System.out.println(data[i / 100].getJSONObject(i % 100)
						.toString());
		} else {
			boolean find = false;
			for (int i = 0; i < N && !find; i++) {
				for (; current[i] > i
						&& sum - price[current[i]] + price[current[i] - 1] >= target; current[i]--) {
					sum = sum - price[current[i]] + price[current[i] - 1];
				}
				if (current[i] > i)
					find = true;
			}
			if (!find) {
				for (int i = 0; i < N; i++)
					System.out.println(data[current[i] / 100].getJSONObject(
							current[i] % 100).toString());
				return;
			}
			int[][] select = new int[N + 1][];
			for (int i = 0; i <= N; i++) {
				select[i] = new int[sum + 1];
				for (int j = 0; j <= sum; j++) {
					select[i][j] = -1;
				}
			}
			select[0][0] = n + 1;
			for (k = 0; k < n; k++) {
				for (int j = sum; j >= price[k]; j--) {
					for (int i = N - 1 < k ? N - 1 : k; i >= 0; i--) {
						if (select[i][j - price[k]] >= 0
								&& select[i + 1][j] < 0) {
							select[i + 1][j] = k;
						}
					}
				}
			}
			for (int i = 0; i <= sum - target; i++) {
				if (select[N][target - i] >= 0) {
					int ptr = target - i;
					for (k = N - 1; k >= 0; k--) {
						ans[k] = select[k + 1][ptr];
						ptr -= price[select[k + 1][ptr]];
					}
					break;
				}
				if (select[N][target + i] >= 0) {
					int ptr = target + i;
					for (k = N - 1; k >= 0; k--) {
						ans[k] = select[k + 1][ptr];
						ptr -= price[select[k + 1][ptr]];
					}
					break;
				}
			}
			for (int i = 0; i < N; i++)
				System.out.println(data[ans[i] / 100].getJSONObject(
						ans[i] % 100).toString());
		}

	}
}
