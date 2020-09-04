package project1;

// 랜덤으로 포인트 득점
public class Point implements IGame{
	
	public int gamePlay() {
		// 새로운 게임이 시작되면 각 포인트점수 0으로 초기화
		Set.ApointScore = 0;
		Set.BpointScore = 0;
		
		do {
			// 포인트점수를 가져가는 플레이어는 랜덤으로 지정
			pointWinner((int)(Math.random()*2)+1);	// 1 or 2 반환
			if(Set.ApointScore == 3 && Set.BpointScore == 3){		// 포인트 듀스 3:3(40:40)일 때,
				while(true) {
					dispScoreBoard();		// 포인트획득 결과 출력
					pointWinner((int)(Math.random()*2)+1);
					if(Set.ApointScore==4 && Set.BpointScore ==4) {		// 4:4(40A:40A)로 듀스라면
						dispScoreBoard();
						Set.ApointScore--;		// 각 포인트점수를 -1 해서 3(40)으로 다시 세팅
						Set.BpointScore--;
					}
					// 두 플레이어의 포인트점수 차가 2가 될때까지 무한루프..
					if(Math.abs(Set.ApointScore-Set.BpointScore)==2) break;		// 누가 이기든 2점차가 되면 break; 무한루프를 빠져나가면 겉의 while문 조건을 충족 못하므로 겉의 while문도 빠져나감
				}
			}
			dispScoreBoard();
		} while(Set.ApointScore < 4 && Set.BpointScore < 4);		// 듀스가 없더라도 4포인트(40A) 선점하면 while문 빠져나감
		
		return Set.ApointScore > Set.BpointScore ? 1 : 2;
	}

	@Override
	public void pointWinner(int p) {
		if(p==1) Set.ApointScore++;
		else  Set.BpointScore++;
	}

	@Override
	public void dispScoreBoard() {
		Disp d = new Disp();
		String str = d.printBoard(Set.Aset, Set.Bset, Set.AsetScore, Set.AgameScore, Set.ApointScore, Set.BsetScore, Set.BgameScore, Set.BpointScore);
		System.out.println(str);
	}
	
}
