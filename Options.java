public class Options extends Math {

    // Generates options
    public String[] generateOptions(int correctIndex) {
        String[] optionList = new String[4];
        for (int i = 0; i < 4; i++) {
            if (i == correctIndex)
                optionList[i] = String.valueOf(target);
            else
                optionList[i] = String.valueOf(generateRandomNumber());
        }

        return optionList;
    }
}
