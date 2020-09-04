package project1;

public class Disp {

	public String printBoard(int[] Aset, int[] Bset, int AsetScore, int AgameScore, int ApointScore, int BsetScore, int BgameScore, int BpointScore) {

			// 포인트 점수가       0    1    2     3     4      5  일 때의 화면에 출력되는 값을 배열에 저장
		String [] Apoint = {"0","15","30","40","40A","Win"};
		String [] Bpoint = {"0","15","30","40","40A","Win"};

		StringBuffer br = new StringBuffer();
		br.append("---------------------------------------------------------------------------\n");
		br.append("\tSET\n");
		br.append("---------------------------------------------------------------------------\n");
		br.append("1\t2\t3\t4\t5\t\t\tS\tG\tP\n");
		br.append("---------------------------------------------------------------------------\n");

		for (int i = 0; i < Aset.length; i++) {
			br.append(Aset[i] );
			br.append("\t");        
		}

		if (Aset.length==3) {
			br.append("\t\t");

		}
		br.append("[A]\t\t");

		br.append(AsetScore);
		br.append("\t");

		br.append(AgameScore+"\t");
		br.append(Apoint[ApointScore]+"\n");			// ApointScore를 인덱스번호로 사용하여 실제로 쓰이는 용어 출력

		for (int i = 0; i < Bset.length; i++) {
			br.append(Bset[i] );
			br.append("\t");        
		}

		if (Bset.length==3) {
			br.append("\t\t");

		}

		br.append("[B]\t\t");

		br.append(BsetScore);
		br.append("\t");

		br.append(BgameScore+"\t");
		br.append(Bpoint[BpointScore]+"\n");			// BpointScore를 인덱스번호로 사용하여 실제로 쓰이는 용어 출력
		br.append("---------------------------------------------------------------------------\n");

		return br.toString();
	}

	// 플레이어 출력 메서드, 매개변수로 Setting 클래스의 객체 받음
	public static void printPlayer(String [] players) {
		
		System.out.println("= 선택된 플레이어 =");
		
		if(players.length==2) {		// 플레이어가 2명인 경우,
			System.out.printf("A : %s\n", players[0]);
			System.out.printf("B : %s\n", players[1]);
			Record.writeScoreBoard(String.format("A : %s", players[0]));
			Record.writeScoreBoard(String.format("B : %s", players[1]));
		} else {							// 플레이어가 4명인 경우,
			System.out.printf("A : %s, %s\n", players[0], players[2]);
			System.out.printf("B : %s, %s\n", players[1], players[3]);
			Record.writeScoreBoard(String.format("A : %s, %s", players[0], players[2]));
			Record.writeScoreBoard(String.format("B : %s, %s", players[1], players[3]));
		}
		
		System.out.println();
		
	}

	
}

