package project1;

import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;

// 게임 옵션 설정 클래스
public class Setting {

	//필드
	String gender;		// 플레이어 성별 - 남(1) / 여(2) 혼성(3)
	int setNum;			// 세트횟수	  - 5세트  /  3세트
	String gameType; // 단식(1)/복식(2)
	String tieBreak;	// 타이브레이크 유y/무n
	
	// 순서O 중복X LinkedHashSet 컬렉션클래스 이용, 플레이어명을 String값으로 저장
	LinkedHashSet<String> playerList = new LinkedHashSet<String>();
	
	// 전역변수로 선언된 scanner -> 이 클래스의 메서드 곳곳에서 입력받을 때 사용
	Scanner scanner = new Scanner(System.in);

	// 테니스 게임의 옵션 설정 메서드
	public  void setGameOption() {

		// 성별 설정 메서드 호출
		setGender();
		// 단식/복식 설정 메서드 호출
		setGameType();

		// 성별과 단/복식 설정에 맞춰 플레이어 선택 메서드 호출
		int check;
		do {
			playerList.clear();		// 플레이어를 선택할 때 잘못 입력하면, 잘못 입력하기 전까지 저장된 값들 모두 제거 (playerList 비우기)
			//				게임타입이 단식이라면	singlePlayer() 호출, 아니라면 teamPlayer() 호출
			check = gameType.equals("1") ? singlePlayer() : teamPlayer();
		} while (check == -1);				// 메서드 진행 중, 잘못된 값을 입력하면 -1 반환
		
		// 타이브레이크 설정 메서드 호출
		setTieBreak();
		
		System.out.println("설정 완료\n");
		
	}
	
	// 복식일 때 플레이어 설정 메서드
	private int teamPlayer() {
		String [] man = {"김태호","송세종","kenik","이창익"};
		String [] woman = {"김지수","엄예지","장유경","최수지"};

		Scanner s = new Scanner(System.in);
		
		int i = 0;
		int cnt = 1;		// 저장완료 된 플레이어 수 카운트
		while(playerList.size() < 4) {
			try {
				switch (gender) {
				case "1":		// 남자인 경우,
					System.out.printf("[ 1.%s   2.%s   3.%s   4.%s ]\n", man[0], man[1], man[2], man[3]);
					System.out.printf("남자 플레이어(%d) 선택 ? ", cnt);
					i = s.nextInt();
					if(playerList.add(man[i-1])) cnt++;				// i에 선택사항에 없는 값 입력 시 ArrayIndexOutOfBoundsException(인덱스 오류) 발생 catch문으로 이동
					// add()가 데이터 저장에 성공하면 true를 반환하므로 cnt++
					break;
				case "2":		// 여자인 경우,
					System.out.printf("[ 1.%s   2.%s   3.%s   4.%s ]\n", woman[0], woman[1], woman[2], woman[3]);
					System.out.printf("여자 플레이어(%d) 선택 ? ", cnt);
					i = s.nextInt();
					if(playerList.add(woman[i-1])) cnt++;			// i에 선택사항에 없는 값 입력 시 ArrayIndexOutOfBoundsException(인덱스 오류) 발생 catch문으로 이동
					break;
				case "3":		// 혼합인 경우,
					if(cnt <= 2) {		// cnt가 2 이하면 실행 -> 입력된 남자 플레이어 수가 2명 이하면 실행
						System.out.printf("[ 1.%s   2.%s   3.%s   4.%s ]\n", man[0], man[1], man[2], man[3]);
						System.out.printf("남자 플레이어(%d) 선택 ? ", cnt);
						i = s.nextInt();
						if(playerList.add(man[i-1])) cnt++;	// i에 선택사항에 없는 값 입력 시 ArrayIndexOutOfBoundsException(인덱스 오류) 발생 catch문으로 이동
					} else {
						System.out.printf("[ 1.%s   2.%s   3.%s   4.%s ]\n", woman[0], woman[1], woman[2], woman[3]);
						System.out.printf("여자 플레이어(%d) 선택 ? ", cnt);
						i = s.nextInt();
						if(playerList.add(woman[i-1])) cnt++;		// i에 선택사항에 없는 값 입력 시 ArrayIndexOutOfBoundsException(인덱스 오류) 발생 catch문으로 이동
					}
				}
			} catch(InputMismatchException | ArrayIndexOutOfBoundsException e) {
				return -1;		// 잘못된 값 입력 시 -1 반환
			}
		}
		return 1;		// 예외 발생 없이 무사히 try문을 빠져나오면 1 반환
	}

	// 단식일 때 플레이어 설정 메서드
	private int singlePlayer() {
		// 플레이어 목록
		String [] man = {"김태호","송세종","kenik","이창익"};
		String [] woman = {"김지수","엄예지","장유경","최수지"};

		Scanner s = new Scanner(System.in);
		int cnt = 1;
		while(playerList.size() < 2) {
			try {
				int i = 0;
				switch (gender) {
				case "1":	// 남자인 경우,
					System.out.printf("[ 1.%s   2.%s   3.%s   4.%s ]\n", man[0], man[1], man[2], man[3]);
					System.out.printf("남자 플레이어(%d) 선택 ? ", cnt);
					i = s.nextInt();
					if(playerList.add(man[i-1])) cnt++;			// i에 선택사항에 없는 값 입력 시 ArrayIndexOutOfBoundsException(인덱스 오류) 발생 catch문으로 이동
					break;
				case "2":	// 여자인 경우,
					System.out.printf("[ 1.%s   2.%s   3.%s   4.%s ]\n", woman[0], woman[1], woman[2], woman[3]);
					System.out.printf("여자 플레이어(%d) 선택 ? ", cnt);
					i = s.nextInt();
					if(playerList.add(woman[i-1])) cnt++;		// i에 선택사항에 없는 값 입력 시 ArrayIndexOutOfBoundsException(인덱스 오류) 발생 catch문으로 이동
					break;
				}
			} catch(InputMismatchException | ArrayIndexOutOfBoundsException e) {
				return -1;		// 잘못된 값 입력 시 -1 반환
			}
		}
		return 1;	// 예외 발생 없이 무사히 try문을 빠져나오면 1 반환
	}

	// 타이브레이크 설정 메서드
	private void setTieBreak() {
		do {
			System.out.print("타이브레이크 적용 [ y/n ]  ?");
			tieBreak = scanner.next();
					// 선택사항에 없는 값을 입력하면 다시..	(대소문자 구분 X)
		} while (!tieBreak.equalsIgnoreCase("y") && !tieBreak.equalsIgnoreCase("n")); 
	}
	
	// 게임타입(단/복식) 설정 메서드
	private void setGameType() {
		if(gender.equals("3")) gameType = "2";		// 플레이어 성별이 혼합이라면 복식으로 고정
		else {
			do {
				System.out.print("[ 1.단식   2.복식 ]\n선택 ? ");
				gameType = scanner.next();
						// 선택사항에 없는 값을 입력하면 다시..
			} while (!gameType.equals("1") && !gameType.equals("2"));
		}
	}

	// 플레이어 성별 설정 메서드
	private void setGender() {
		do {
			System.out.print("[ 1.남자   2.여자   3.혼성 ]\n선택 ? ");
			gender = scanner.next();

			if(gender.equals("1")) { 	
				setNum = 5;	// 남자 설정시 경기는 5세트 진행
				break;
			} else if(gender.equals("2") || gender.equals("3")){ 	
				setNum = 3;	// 여자 또는 혼합 설정시 경기는 3세트 진행
				break;
			}
					// 선택사항에 없는 값을 입력하면 다시..
		} while (!(gender.equals("1") || gender.equals("2") || gender.equals("3")));
	}
}
