# nbc_kiosk
Terminal 기반 키오스크 시뮬레이터

## 기능
- 아이템 추가 (수량 기반)
- 아이템 구매
- 아이템 선택 취소 (수량 기반)

## 요구사항
- Java 21 이상 (이하의 버젼에는 오류가 발생할 가능성 있음)
- 외부 라이브러리 & 빌드툴 없음

## 실행법
- 개발 환경에서의 실행
- clone 시행
```bash
git clone https://github.com/Over-night/nbc_kiosk
cd nbc_kiosk
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out Main