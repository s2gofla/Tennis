package project1;

// 타이브레이크 적용한 게임
public class TieGame implements IGame {
	
	public int setPlay() {
		// 새로운 세트가 시작되면 각 게임점수 0으로 초기화
		Set.AgameScore = 0;
		Set.BgameScore = 0;

		Point p = new Point();		// Point클래스의 gameplay() 사용하기 위해 객체 생성
		
		do {
			pointWinner(p.gamePlay());	// 한 게임의 결과를 반환하여 해당 플레이어의 게임점수++
			dispScoreBoard();			// 한 게임 결과 화면에 출력
			if(Set.AgameScore == 5 && Set.BgameScore == 5){	// 5:5 듀스일 때
				pointWinner(p.gamePlay());		// 한 게임,
				dispScoreBoard();	
				pointWinner(p.gamePlay());		// 두 게임 더해서
				dispScoreBoard();
				if(Set.AgameScore == 7 || Set.BgameScore == 7) break;	// 누구든 연속 2점 획득하여 5:7 또는 7:5가 되면 break; while문 조건에 충족하지 않기때문에 그대로 while문을 벗어난다.
				else {										// 연속으로 2득점을 하지 못해 6:6이 되면,
					pointWinner(p.gamePlay());		// 한 게임만 더해서 승자 결정.
					dispScoreBoard();	
				}
			}
		} while(Set.AgameScore < 6 && Set.BgameScore < 6);		// 듀스가 없더라도 6게임 선점하면 while문 빠져나감
		
		return Set.AgameScore > Set.BgameScore ? 1 : 2;

	}

	@Override
	public void pointWinner(int p) {
		if(p==1) Set.AgameScore++;
		else  Set.BgameScore++;
	}

	@Override
	public void dispScoreBoard() {
		Disp d = new Disp();
		String str = d.printBoard(Set.Aset, Set.Bset, Set.AsetScore, Set.AgameScore, Set.ApointScore, Set.BsetScore, Set.BgameScore, Set.BpointScore);
		System.out.println(str);
	}
	
}
