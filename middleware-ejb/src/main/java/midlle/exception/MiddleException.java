/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midlle.exception;
import javax.ejb.ApplicationException;
/**
 *
 * @author TOSHIBA
 */
@ApplicationException(rollback=true)
public class MiddleException extends RuntimeException {
 // champs privés
 private int code = 0;
 // constructeurs
public MiddleException() {
 super();
 }
public MiddleException(String message) {
 super(message);
 }
public MiddleException(String message, Throwable cause) {
 super(message, cause);
 }
public MiddleException(Throwable cause) {
 super(cause);
 }
public MiddleException(String message, int code) {
 super(message);
 setCode(code);
 }
public MiddleException(Throwable cause, int code) {
 super(cause);
 setCode(code);
 }
public MiddleException(String message, Throwable cause, int code) {
 super(message, cause);
 setCode(code);
 }
// getters and setters
 public int getCode() {
 return code;
 }
 public void setCode(int code) {
 this.code = code;
 }
}
