package jp.ac.nagano_nct.ashida_lab.se;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * カメラ
 * @author ashida
 */
public class Camera {
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
			_format = format;
		}

		/**
		 * フォーマットを得る
		 * @return フォーマット
		 */
		public String getFormat() {
			return _format;
		}

	}
	/**
	 * シングルトンのインスタンス
	 */
	private static Camera _instance;

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
	private static final String _DEFAULT_FILE_NAME = "image";

	/**
	 * デフォルトのディレクトリ名
	 */
	private static final String _DEFAULT_DIRECTORY_NAME = "/home/rpi-car/Pictures";

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
		if(_instance == null){
			_instance = new Camera();
			_instance._format = _DEFAULT_FORMAT;
			_instance._width = _DEFAULT_WIDTH;
			_instance._height = _DEFAULT_HEIGHT;
			_instance._fileName = _DEFAULT_FILE_NAME;
			_instance._directoryName = _DEFAULT_DIRECTORY_NAME;		}
		return _instance;
	}

	/**
	 * 写真を撮影する
	 * @return 撮影したファイル
	 * @throws CameraRuntimeException カメラ実行時エラー
	 */
	public File takePicture() throws CameraRuntimeException{
		/* ファイル名を得る */
		String absolute_file_path = String.format("%s/%s.%s", _directoryName, _fileName, _format.getFormat());
		String command = String.format("libcamera-still -n --rotation 180 -o %s --width %d --height %d --encoding %s", absolute_file_path, _width, _height, _format.getFormat());
		ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

		int exit_code = 0;
		try {
            exit_code = _callBash(processBuilder);
        } catch (Exception e) {
        }

		if(exit_code != 0){
			throw new CameraRuntimeException(exit_code);
		}
		return new File(absolute_file_path);
	}

	/**
	 * フォーマットを設定する
	 * @param format フォーマット
	 * @return Cameraインスタンス
	 */
	public Camera setFormat(Camera.Format format) {
		_format = format;
		return this;
	}

	/**
	 * フォーマットを得る
	 * @return フォーマット
	 */
	public Camera.Format getFormat() {
		return _format;
	}

	/**
	 * 画像の幅を設定する
	 * @param width 画像の幅
	 * @return Cameraインスタンス
	 * @throws InvalidImageSizeException 不正な画像サイズが設定されたとき
	 */
	public Camera setWidth(int width) throws InvalidImageSizeException{
		if(width <= 0){
			throw new InvalidImageSizeException(width);
		}
		_width = width;
		return this;
	}

	/**
	 * 画像の幅を得る
	 * @return 画像の幅
	 */
	public int getWidth() {
		return _width;
	}

	/**
	 * ファイル名を設定する
	 * @param file_name ファイル名
	 * @return Cameraインスタンス
	 */
	public Camera setFileName(String file_name) {
		_fileName = file_name;
		return this;
	}

	/**
	 * ファイル名を得る
	 * @return ファイル名
	 */
	public String getFileName() {
		return _fileName;
	}

	/**
	 * ディレクトリ名を設定する
	 * @param directory_name ディレクトリ名
 	 * @return Cameraインスタンス
	 * @throws InvalidDirectoryNameException 不正なディレクトリ名であった場合の例外
	 */
	public Camera setDirectoryName(String directory_name) throws InvalidDirectoryNameException{
		if(!new File(directory_name).isDirectory()){
			throw new InvalidDirectoryNameException(directory_name);
		}
		_directoryName = directory_name;
		return this;
	}

	/**
	 * ディレクトリ名を得る
	 * @return ディレクトリ名
	 */
	public String getDirectoryName() {
		return _directoryName;
	}

	/**
	 * 画像の高さを設定する
	 * @param height 画像の高さ
	 * @return Cameraインスタンス
	 * @throws InvalidImageSizeException 不正な画像サイズが設定されたとき
	 */
	public Camera setHeight(int height) throws InvalidImageSizeException{
		if(height <= 0){
			throw new InvalidImageSizeException(height);
		}
		_height = height;
		return this;
	}

	/**
	 * 画像の高さを得る
	 * @return 画像の高さ
	 */
	public int getHeight() {
		return _height;
	}


	/** BASHでコマンドを呼び出す
	 * @param process_builder プロセスビルダ
	 * @return 実行時に得られる戻り値
	 * @throws IOException 入出力エラー
	 * @throws InterruptedException 中断エラー
	 */
    private int _callBash(ProcessBuilder process_builder) throws IOException, InterruptedException {
        Process process = process_builder.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        //exitCode 0 = No Errors
        int exit_code = process.waitFor();
        if(exit_code != 0){
            System.out.println("\nCamera exited with error code : " + exit_code);
        }else{
            System.out.println("\nCamera finished successfully");
        }

		return exit_code;
    }
}
