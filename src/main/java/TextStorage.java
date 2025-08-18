import java.util.ArrayList;

public class TextStorage {
    private final ArrayList<String> textList = new ArrayList<>();

    public String addItem(String item) {
        textList.add(item);
        return "i have added \"" + item + "\"";
    }

    public String viewList() {
        StringBuilder msg = new StringBuilder();
        String itemString;

        msg.append("\n");
        for (int i = 0; i < textList.size(); i++) {
            //Each text entry displayed as "X. textItem"
            itemString = (i + 1) + ". " + textList.get(i) + "\n";
            msg.append(itemString);
        }

        if (textList.isEmpty()) {
            //Account for empty textList
            return "The list is empty!";
        } else {
            //Remove last "\n" for formatting purposes
            return msg.substring(0, msg.length() - 1);
        }
    }
}
