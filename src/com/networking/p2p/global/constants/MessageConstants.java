package com.networking.p2p.global.constants;
public enum MessageConstants {
    INPUT_PEER_INFORMATION("> peer의 이름과 포트 번호를 입력하세요. (peer의 이름과 포트 구분은 공백으로 구분합니다.)"),
    INPUT_DESTINATION_INFORMATION_TO_RECEIVE_THE_MESSAGE("""
            > 메시지를 수신할 대상 peer들에 접근하기 위한 정보를 입력하세요. (입력 예시:: hostname:port)
            > 만약, s를 입력하면 skip합니다."""),

    INVALID_INPUT_NEXT_STEP("invalid input. skipping to next step."),
    CAN_SEND_AND_RECEIVE_MESSAGES("""
                > 이제 peer끼리 메시지를 주고 받을 수 있습니다.
                  더 이상 진행을 원하지않거나, 변경을 원할 경우 아래의 명령어를 입력하세요. 
                  (e : exit, c : change)""");

    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
