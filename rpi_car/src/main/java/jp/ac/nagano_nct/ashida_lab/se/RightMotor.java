package jp.ac.nagano_nct.ashida_lab.se;

/**
 * 右側モータ
 * @author ashida
 */
public class RightMotor extends Motor {

	/**
	 * 端子1の番号
	 */
	private static final int _PIN1_NUM = 17;

	/**
	 * 端子2の番号
	 */
	private static final int _PIN2_NUM = 24;

	/**
	 * PWMの端子番号
	 */
	private static final int _PWM_PIN_NUM = 22;

	/**
	 * シングルトンのインスタンス
	 */
	private static RightMotor _instance = new RightMotor(_PIN1_NUM, _PIN2_NUM, _PWM_PIN_NUM);

	/**
	 * コンストラクタ
	 * @param pin_num1 端子番号1
	 * @param pin_num2 端子番号2
	 * @param pwm_pin_num PWM用端子番号
	 */
	private RightMotor(int pin_num1, int pin_num2, int pwm_pin_num) {
		super(pin_num1, pin_num2, pwm_pin_num);
	}

	/**
	 * インスタンスを得る
	 * @return インスタンス
	 */
	public static RightMotor getInstance() {
		return _instance;
	}


}
