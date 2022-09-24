public class Exceptions {
    private static final String AllowedCharacters = "0123456789_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean check (String login, String password, String confirmPassword){
        try {
            validate(login, password, confirmPassword);
        } catch (CheckLogin | CheckPassword e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    private static void validate (String login, String password, String confirmPassword) throws CheckLogin, CheckPassword {
        if (login == null || login.length() > 20) {
            throw new CheckLogin("Длинна логина не должна привышать 20 символов");
        }
        if (password == null || password.length() >= 20) {
            throw new CheckPassword("Длинна пароля должна быть меньше 20 символов");
        }
        if (!password.equals(confirmPassword)) {
            throw new CheckPassword("Пароли должны совпадать");
        }
        validateCharacters(login, true);
        validateCharacters(password, false);
    }

    private static void validateCharacters (String s, boolean isLogin) throws CheckLogin,CheckPassword {
        for (int i = 0; i < s.length(); i++) {
            if (!AllowedCharacters.contains(String.valueOf(s.charAt(i)))) {
                if (isLogin) {
                    throw new CheckLogin("В логине содержится некоректный символ " + s.charAt(i));
                } else {
                    throw new CheckPassword("В пароле содержится некоректный символ " + s.charAt(i));
                }
            }
        }
    }
}
