package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

import static baseball.Constant.*;

public class Computer {
    public static List<Integer> generateNumber() {
        List<Integer> computerAnswer = new ArrayList<>();
        while (computerAnswer.size() < NUMBER_SIZE) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computerAnswer.contains(randomNumber)) {
                computerAnswer.add(randomNumber);
            }
        }
        return computerAnswer;
    }
    public static List<Integer> getPlayerAnswer() {
        System.out.print(INPUT_MESSAGE);
        String playerAnswerStr = Console.readLine();
        List<Integer> playerAnswer = stringToIntegerList(playerAnswerStr);

        validateNumber(playerAnswer);

        return playerAnswer;
    }
    public static List<Integer> stringToIntegerList(String inputStr) {
        String[] inputStrArr = inputStr.split("");
        List<Integer> inputIntList = new ArrayList<>();
        for (int i = 0; i < inputStrArr.length; i++) {
            int tmp = Integer.parseInt(inputStrArr[i]);
            inputIntList.add(tmp);
        }
        return inputIntList;
    }
    public static boolean validateNumber(List<Integer> playerAnswer) {
        checkLength(playerAnswer);
        checkDuplicate(playerAnswer);
        checkNumberOnly(playerAnswer);
        return true;
    }
    private static void checkLength(List<Integer> playerAnswer) {
        if (playerAnswer.size() != NUMBER_SIZE) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }
    private static void checkDuplicate(List<Integer> playerAnswer) {
        if (playerAnswer.size() != playerAnswer.stream().distinct().count()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }
    private static void checkNumberOnly(List<Integer> playerAnswer) {
        String regExp = "^[1-9]+$";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String st = Integer.toString(playerAnswer.get(i));
            sb.append(st);
        }
        String s = sb.toString();
        if (!s.matches(regExp)) {
            throw new IllegalArgumentException(INPUT_ERROR_MESSAGE);
        }
    }
}
