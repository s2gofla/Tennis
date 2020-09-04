package project1;

// 타이브레이크 적용하지않은 게임
public class NoTieGame implements IGame{
	
	
	public int setPlay() {
		// 새로운 세트가 시작되면 각 게임점수 0으로 초기화
		Set.AgameScore = 0;
		Set.BgameScore = 0;
		
		Point p = new Point();		// Point클래스의 gameplay() 사용하기 위해 객체 생성
		
		do {
			pointWinner(p.gamePlay());	// 한 게임의 결과를 반환하여 해당 플레이어의 게임점수++
			dispScoreBoard();			// 한 게임 결과 화면에 출력
			if(Set.AgameScore == 5 && Set.BgameScore == 5){		// 5:5 듀스일 떄,
				while(true) {
					pointWinner(p.gamePlay());
					dispScoreBoard();
					// Math클래스의 절대값구하는 함수abs()이용, 두 플레이어의 게임점수 차가 2가 될때까지 무한루프..
					if(Math.abs(Set.AgameScore-Set.BgameScore)==2) break;	// 누가 이기든 2점차가 되면 break; 무한루프를 빠져나가면 겉의 while문 조건을 충족 못하므로 겉의 while문도 빠져나감
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
