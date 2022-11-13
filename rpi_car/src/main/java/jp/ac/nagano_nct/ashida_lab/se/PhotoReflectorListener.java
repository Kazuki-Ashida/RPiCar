package jp.ac.nagano_nct.ashida_lab.se;

/**
 * フォトリフレクタの状態が変化したときのリスナ
 * @author ashida
 */
public interface PhotoReflectorListener {

	/**
	 * 状態が変化したことを知らせるイベント
	 * @param is_black 黒であるか
	 */
	public abstract void handlePhotoReflectorStateChangeEvent(boolean is_black);

}
