package jp.ac.nagano_nct.ashida_lab.se;
import java.io.File;
import java.time.LocalDateTime;

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
		if(_instance == null){
			_instance = new Camera();
		}
		return _instance;
	}

	/**
	 * 写真を撮影する
	 * @param width 幅
	 * @param height 高さ
	 * @return 撮影したファイル(PNG)
	 */
	public File takePicture(int width, int height) {

		String file_name = LocalDateTime.now().toString()+"."+com.pi4j.catalog.components.Camera.PicEncoding.PNG.getEncoding();
		var config = com.pi4j.catalog.components.Camera.PicConfig.Builder.newInstance().outputPath("/home/rpi-car/Pictures/"+file_name)
		.delay(3000)
		.disablePreview(true)
		.encoding(com.pi4j.catalog.components.Camera.PicEncoding.PNG)
		.useDate(false)
		.quality(93)
		.width(width)
		.height(height)
		.build();

		com.pi4j.catalog.components.Camera camera= new com.pi4j.catalog.components.Camera();
		camera.takeStill(config);

	
		return new File("/home/rpi-car/Pictures/"+file_name);
	}

	/**  ビデオを撮影する
	 * @param 撮影時間(秒)
 	 * @return 撮影したビデオ(MP4)
	 */
	public File takeVideo(int time){
		return null;
	}

}
