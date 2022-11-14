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
	 * @param width 幅
	 * @param height 高さ
	 * @return 撮影したファイル(PNG)
	 */
	public File takePicture(int width, int height) {
/*
		var config = Camera.PicConfig.Builder.outputPath("/home/rpi_car/Pictures/")
		.delay(3000)
		.disablePreview(true)
		.encoding(Camera.PicEncoding.PNG)
		.useDate(true)
		.quality(93)
		.width(width)
		.height(height)
		.build();

		camera.takeStill(config);
		*/
		return null;
	}

	/**  ビデオを撮影する
	 * @param 撮影時間(秒)
 	 * @return 撮影したビデオ(MP4)
	 */
	public File takeVideo(int time){
		return null;
	}

}
