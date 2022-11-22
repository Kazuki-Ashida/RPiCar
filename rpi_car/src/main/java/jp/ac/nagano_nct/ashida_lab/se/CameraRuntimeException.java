package jp.ac.nagano_nct.ashida_lab.se;

/** カメラ実行時エラー
 * @author 芦田
 */
public class CameraRuntimeException extends RuntimeException{
    /** エラーコード */
    private int _errorCode;

    /** コンストラクタ
     * @param error_code エラーコード
     */
    public CameraRuntimeException(int error_code){
        _errorCode = error_code;
    }

    /** エラーコードを得る
     * @return エラーコード
     */
    public int getErrorCode(){
        return _errorCode;
    }
}