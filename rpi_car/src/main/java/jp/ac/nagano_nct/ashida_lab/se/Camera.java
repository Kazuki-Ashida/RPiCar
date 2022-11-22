package jp.ac.nagano_nct.ashida_lab.se;

import java.io.File;

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
	 * 画像の幅
	 */
	private int _width;

	/**
	 * 画像の高さ
	 */
	private int _height;

	/**
	 * ファイル名
	 */
	private String _fileName;

	/**
	 * ディレクトリ名
	 */
	private String _directoryName;

	/**
	 * フォーマット
	 */
	private Camera.Format _format;

	/**
	 * デフォルトの画像幅
	 */
	private static final int _DEFAULT_WIDTH = 1280;

	/**
	 * デフォルトの画像高
	 */
	private static final int _DEFAULT_HEIGHT = 1024;

	/**
	 * デフォルトのファイル名
	 */
	private static final String _DEFAULT_FILE_NAME = image;

	/**
	 * デフォルトのディレクトリ名
	 */
	private static final String _DEFAULT_DIRECTORY_NAME = /home/rpi-car/Pictures;

	/**
	 * デフォルトのフォーマット
	 */
	private static final Camera.Format _DEFAULT_FORMAT = Format.PNG;

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
	 * 写真を撮影する
	 * @return 撮影したファイル(PNG)
	 */
	public File takePicture(int width, int height) {
		return null;
	}

	/**
	 * フォーマットを設定する
	 * @param format フォーマット
	 */
	public void setFormat(Camera.Format format) {

	}

	/**
	 * フォーマットを得る
	 * @return フォーマット
	 */
	public Camera.Format getFormat() {
		return null;
	}

	/**
	 * 画像の幅を設定する
	 * @param width 画像の幅
	 * @throws InvalidImageSizeException 不正な画像サイズが設定されたとき
	 */
	public void setWidth(int width) {

	}

	/**
	 * 画像の幅を得る
	 * @return 画像の幅
	 */
	public int getWidth() {
		return 0;
	}

	/**
	 * ファイル名を設定する
	 * @param file_name ファイル名
	 */
	public void setFileName(String file_name) {

	}

	/**
	 * ファイル名を得る
	 * @return ファイル名
	 */
	public String getFileName() {
		return null;
	}

	/**
	 * ディレクトリ名を設定する
	 * @param directory_name ディレクトリ名
	 */
	public void setDirectoryName(String directory_name) {

	}

	/**
	 * ディレクトリ名を得る
	 * @return ディレクトリ名
	 */
	public String getDirectoryName() {
		return null;
	}

	/**
	 * 画像の高さを設定する
	 * @param height 画像の高さ
	 * @throws InvalidImageSizeException 不正な画像サイズが設定されたとき
	 */
	public void setHeight(int height) {

	}

	/**
	 * 画像の高さを得る
	 * @return 画像の高さ
	 */
	public int getHeight() {
		return 0;
	}

	/**
	 * 画像フォーマット
	 * @author 芦田
	 */
	public enum Format {

		/**
		 * ポータブルネットワークグラフィックス
		 */
		PNG("png"),

		/**
		 * Joint Photographic Experts Group
		 */
		JPG("jpg"),

		/**
		 * RGB
		 */
		RGB("rgb"),

		/**
		 * ビットマップ
		 */
		BMP("bmp"),

		/**
		 * YUV420
		 */
		YUV420("yuv420");

		/**
		 * フォーマット名
		 */
		private final String _format;

		/**
		 * コンストラクタ
		 * @param format フォーマット名
		 */
		private Format(String format) {

		}

		/**
		 * フォーマットを得る
		 * @return フォーマット
		 */
		public String getFormat() {
			return null;
		}

	}

}
