package jp.ac.nagano_nct.ashida_lab.se;

/**
 * カメラ
 * @author ashida
 */
public class Camera {

	/**
	 * シングルトンのインスタンス
	 */
	private static Camera _instance = new Camera();

	/**
	 * コンストラクタ
	 */
	private Camera() {

	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static Camera getInstance() {
		return null;
	}

	/**
	 * 撮影する
	 */
	public void takePicture() {

	}

}
