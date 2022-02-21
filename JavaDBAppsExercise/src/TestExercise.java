import addMinion.AddMinion;
import changeTownNameCasing.ChangeTownNameCasing;
import getMinionsNames.GetMinionNames;
import getVilliansName.GetVillainName;
import increaseAgeStoredProcedure.IncreaseAgeStoredProcedure;
import increaseMinionAge.IncreaseMinionsAge;
import printAllMinionsNames.PrintAllMinionNames;
import removeVillain.RemoveVillain;

import java.sql.SQLException;
import java.util.Scanner;

public class TestExercise {
    public static void main(String[] args) throws SQLException {

        /* Run this class and choose exercise you want to test.
        Default DB username is root press Enter to skip it or enter you DB username,
        next enter your password.*/

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter exercise number: ");

        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 2 -> GetVillainName.main(args);
            case 3 -> GetMinionNames.main(args);
            case 4 -> AddMinion.main(args);
            case 5 -> ChangeTownNameCasing.main(args);
            case 6 -> RemoveVillain.main(args);
            case 7 -> PrintAllMinionNames.main(args);
            case 8 -> IncreaseMinionsAge.main(args);
            case 9 -> IncreaseAgeStoredProcedure.main(args);
            default -> throw new IllegalStateException("Incorrect exercise number " + number);
        }
    }
}
