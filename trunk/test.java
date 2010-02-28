public class test {

	public static void main(String[] args) {
		int a = 7;
		int s = 1;
		for (int i = 0; i < 4; i++) {
			System.out.println(s);
			for (int k = 0; k < 4; k++) {
				System.out.println(s);
			}
			for (int l = 0; l < 4; l++) {
				System.out.println(s);
				for (int n = 0; n < 4; n++) {
					System.out.println(s);
				}
			}
		}
		for (int m = 0; m < 4; m++) {
			System.out.println(s);
		}

	}

	public void cykly() {
		for (int m = 0; m < 4; m++) {
			for (int a = 0; a < 4; a++) {
				for (int b = 0; b < 4; b++) {
					for (int c = 0; c < 4; c++) {
						for (int d = 0; d < 4; d++) {
						}
					}
				}
			}
		}
	}
}