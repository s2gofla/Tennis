package project1;

import java.io.IOException;
import java.util.Iterator;

public class Set implements IGame{

	// 필드
	// static으로 설정 -> 각 점수를 누적, 출력할 때 사용하기 편함
	static int AsetScore = 0;		// A의 세트점수
	static int BsetScore = 0;		// B의 세트점수
	static int AgameScore = 0;	// A의 게임점수
	static int BgameScore = 0;	// B의 게임점수
	static int ApointScore = 0;	// A의 포인트점수
	static int BpointScore = 0;	// B의 포인트점수

	// 한 세트마다 결과(각 게임점수)를 저장하기 위한 배열 -> dispScoreBoard()에서 출력할 때 사용
	static int [] Aset = null;
	static int [] Bset = null;
	
	// 플레이어들의 이름을 저장할 전역변수
	String [] players = null;
	
	// 테니스게임 시작 메서드 : Setting클래스의 게임 옵션 세팅 메서드 호출, 세팅된 플레이어 출력
	public void gameStart() throws IOException {
		
		// 게임 옵션 세팅 (성별(세트횟수), 단/복식, 타이브레이크 유무)
		Setting s = new Setting();
		s.setGameOption();
		
		// 실행할 세트 횟수(setNum)의 크기로 배열 생성
		Aset = new int[s.setNum];
		Bset = new int[s.setNum];
		
		// 반복자(Iterator)를 통해 설정된 플레이어들의 이름들 반환 
		Iterator<String> it = s.playerList.iterator();
		players = new String[s.gameType.equals("1") ? 2 : 4];
		int i = 0;								 // 단식인 경우, 플레이어는 2명 : 아니면 4명
		while (it.hasNext()) {
			players[i++] = it.next();
		}
		
		// 플레이어 출력 메서드 호출, Setting클래스의 객체 s를 매개변수로 넘겨준다.
		Disp.printPlayer(players);
		
		System.out.println("엔터를 누르면 게임 시작");
		System.in.read();
		System.in.skip(System.in.available());
		
		// 세트 게임 진행 메서드 호출
		setGame(s);

	}

	// 세트 게임 진행 메서드
	public void setGame(Setting s) {
		int idx = 0;	// 한 세트마다 각 게임점수를 저장하는 Aset[], Bset[]의 인덱스 변수 
		
		do {																	// 일회용객체 생성해서 setPlay() 호출
			int setWin = s.tieBreak.equalsIgnoreCase("y") ? new TieGame().setPlay() : new NoTieGame().setPlay();
			// 어느 팀이 승리했나에 따라 1 또는 2 반환
			pointWinner(setWin);	// 이긴 팀의 세트점수를 더해주는 메서드 호출
			
			// 세트마다 최종 게임 점수를 저장
			Aset[idx] = AgameScore;
			Bset[idx++] = BgameScore;

			// 한 세트 결과를 보여주는 메서드
			dispScoreBoard();
			
		} while (AsetScore <= s.setNum/2 && BsetScore <= s.setNum/2);
					// 설정된 세트횟수 setNum을 2로 나눈 몫보다 커지면 while문 빠져나온다. -> 어느 팀이든 몇 세트냐에 따라 2세트, 3세트 선점하면 게임 종료
		
		String winner;
		// A가 이겼다면.. 							 플레이어가 2명이면,		A (플레이어명) 저장	: 	그렇지않다면(4명이라면),  A(플레이어1, 플레이어2) 저장
		if(AsetScore > BsetScore) winner = players.length==2 ? String.format("A ( %s )", players[0]) : String.format("A ( %s, %s )", players[0], players[2]);
		else winner = players.length==2 ? String.format("B ( %s )", players[1]) : String.format("B ( %s, %s )", players[1], players[3]);
		
		System.out.printf("WINNER - %s\n", winner);
		System.out.println("= END =");
	}

	// 이긴 팀의 세트점수를 더해주는 메서드
	@Override
	public void pointWinner(int p) {
		if(p==1) AsetScore++;
		else  BsetScore++;
	}

	// 세트 결과 보여주는 메서드
	@Override
	public void dispScoreBoard() {
		Disp d = new Disp();
		String str = d.printBoard(Aset, Bset, AsetScore, AgameScore, ApointScore, BsetScore, BgameScore, BpointScore);
		System.out.println(str);
		
		// 세트마다 나오는 결과화면을 파일에 저장하는 메서드 호출
		Record.writeScoreBoard(str);
	}

}
