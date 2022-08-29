package ai.azati;

import ai.azati.service.InfoService;
import ai.azati.util.Validator;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        InfoService infoService = new InfoService();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (true) {
            System.out.print("Input country name: ");
            input = reader.readLine();
            if (Validator.validate(input)) {
                break;
            }
            System.out.println("Wrong input, try again");
        }
        try {
            System.out.println(infoService.stringInfoAboutCountry(input));
        } catch (Exception ex) {
            log.error("While getting cases: ", ex);
        }
    }
}
