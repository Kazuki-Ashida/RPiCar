package jp.ac.nagano_nct.ashida_lab.se;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmType;

/**
 * モータ
 * @author ashida
 */
public class Motor {

	/** 周波数[Hz] */
	private static final int _FREQUENCY = 30;
	/**
	 * 速度(0～100)
	 */
	private int _speed = 0;

	/** 端子1  */
	private DigitalOutput _pin1;
	/** 端子2  */
	private DigitalOutput _pin2;
	/** PWM端子  */
	private Pwm _pwm;

	/** 速度の係数 */
	private double _coefficient = 1.0F;

	/** コンストラクタ
	 * @param pin_num1 端子1
	 * @param pin_num2 端子2
	 * @param pwm_pin_num PWM端子
	 */
	protected Motor(int pin_num1, int pin_num2, int pwm_pin_num){
		Context pi4j = Pi4J.newAutoContext();
		var config1 = DigitalOutput.newConfigBuilder(pi4j)
                        .id("motorPin1")
                        .name("Motor Pin 1")
                        .address(pin_num1)
                        .shutdown(DigitalState.LOW)
                        .initial(DigitalState.LOW)
                        .provider("pigpio-digital-output");
		_pin1 = pi4j.create(config1);

		var config2 = DigitalOutput.newConfigBuilder(pi4j)
						.id("motorPin2")
						.name("Motor Pin 2")
						.address(pin_num2)
						.shutdown(DigitalState.LOW)
						.initial(DigitalState.LOW)
						.provider("pigpio-digital-output");
		_pin2 = pi4j.create(config2);

		_pwm = pi4j.create(buildPwmConfig(pi4j, pwm_pin_num));


	}
	/**
	 * 回転方向
	 */
	private RotateDirection _rotateDirection = RotateDirection.CLOCKWISE;

	/**
	 * 回転させる
	 * @param speed 速度(0-100)
	 * @param direction 回転方向
	 */
	public void rotate(int speed, RotateDirection direction) {
		if(_speed<0 || _speed >100){
			return;
		}
		_coefficient = 1.0F;
		_speed = speed;
		_rotateDirection = direction;
		switch(_rotateDirection){
			case CLOCKWISE:
				_pin1.high();
				_pin2.low();
				break;
			case COUNTER_CLOCKWISE:
				_pin1.low();
				_pin2.high();
				break;
		}
		_pwm.on(_speed*_coefficient, _FREQUENCY);
	}

	/**
	 * ブレーキをかける
	 */
	public void brake() {
		_speed = 0;
		_pin1.low();
		_pin2.low();
		_pwm.off();
	}

	/**
	 * スピードを得る
	 * @return スピード
	 */
	public int getSpeed() {
		return _speed;
	}

	/**
	 * 回転方向を得る
	 * @return 回転方向
	 */
	public RotateDirection getRotateDirection() {
		return _rotateDirection;
	}



	/**
     * Builds a new PWM configuration for the buzzer
     *
     * @param pi4j    Pi4J context
     * @param address BCM pin address
     * @return PWM configuration
     */
    protected static PwmConfig buildPwmConfig(Context pi4j, int address) {
        return Pwm.newConfigBuilder(pi4j)
            .id("BCM" + address)
            .name("Motor")
            .address(address)
            .pwmType(PwmType.SOFTWARE)
            .provider("pigpio-pwm")
            .initial(0)
            .shutdown(0)
            .build();
    }

	/** 速度の係数を設定する
	 * @param coefficient 係数
	 */
	public void setCoefficient(double coefficient){
		_coefficient = coefficient;
		_pwm.setDutyCycle(_speed*_coefficient);
	}
}
