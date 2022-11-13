package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalStateChangeListener;

/**
 * 電源管理
 * @author ashida
 */
public class PowerManager {

	/** スイッチが押されている時間(単位はミリ秒)．
	 * この時間を超えると，リブートもしくはシャットダウンする．
	 */
	private static final int _EXECUTE_MINUTES = 5000;
	/**
	 * 再起動をするとき押されるスイッチとつながっている端子番号
	 */
	private static final int _REBOOT_SW_PIN_NUM = 14;

	/**
	 * シャットダウンするとき押されるスイッチの端子番号
	 */
	private static final int _SHUTDOWN_SW_PIN_NUM = 4;

	/**
	 * シングルトンのインスタンス
	 */
	private static PowerManager _instance = new PowerManager();

	/** リブートスイッチを操作するためのインスタンス */
	private DigitalInput _rebootSwitchPin;

	/** シャットダウンスイッチを操作するためのインスタンス */
	private DigitalInput _shutdownSwitchPin;

	/**
	 * コンストラクタ
	 */
	private PowerManager() {
		Context pi4j = Pi4J.newAutoContext();
		var config = DigitalInput.newConfigBuilder(pi4j)
				.id("rebootSwitch")
				.name("RebootSwitch")
				.address(_REBOOT_SW_PIN_NUM)
				.provider("pigpio-digital-input");
		_rebootSwitchPin = pi4j.create(config);

		_rebootSwitchPin.addListener(DigitalStateChangeListener ->{
			long start_time = System.currentTimeMillis();
			while(_rebootSwitchPin.isLow()){
				if(start_time+_EXECUTE_MINUTES < System.currentTimeMillis()){
					/* リブートする */
					try {
						Runtime runtime = Runtime.getRuntime();
						Process p = runtime.exec
								("sudo reboot");
						p.waitFor(); // プロセス終了を待つ
						p.destroy(); // プロセスを明確に終了させ、資源を回収
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		});

		config = DigitalInput.newConfigBuilder(pi4j)
				.id("shutdownSwitch")
				.name("ShutdownSwitch")
				.address(_SHUTDOWN_SW_PIN_NUM)
				.provider("pigpio-digital-input");
		_shutdownSwitchPin = pi4j.create(config);
		_shutdownSwitchPin.addListener(DigitalStateChangeListener ->{
			long start_time = System.currentTimeMillis();
			while(_shutdownSwitchPin.isLow()){
				if(start_time+_EXECUTE_MINUTES < System.currentTimeMillis()){
					/* シャットダウンする */
					try {
						Runtime runtime = Runtime.getRuntime();
						Process p = runtime.exec
								("sudo shutdown -h now");
						p.waitFor(); // プロセス終了を待つ
						p.destroy(); // プロセスを明確に終了させ、資源を回収
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		});
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static PowerManager getInstance() {
		return _instance;
	}


}
