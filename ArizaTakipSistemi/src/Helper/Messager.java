package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Messager {

	public static void buttonTextChange() {
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.cancelButtonText", "Iptal");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hayir");
	}

	public static void showMessage(String str) {
		String msg;

		buttonTextChange();

		switch (str) {

		case "fill":
			msg = "Lutfen gerekli alanlari doldurunuz!";
			break;
		case "success":
			msg = "Islem basariyla tamamlandi.";
			break;
		case "error":
			msg = "Hata";
			break;

		default:
			msg = str;
		}

		JOptionPane.showConfirmDialog(null, msg, "Mesaj", JOptionPane.CLOSED_OPTION);

	}

	public static boolean confirm(String str) {
		String msg;

		buttonTextChange();

		switch (str) {

		case "del":
			msg = "Silmek istediginize emin misiniz?";
			break;

		default:
			msg = str;
		}

		int cevap = JOptionPane.showConfirmDialog(null, msg, "Mesaj", JOptionPane.YES_NO_OPTION);

		if (cevap == 0) {
			return true;
		} else {
			return false;
		}
	}
}
