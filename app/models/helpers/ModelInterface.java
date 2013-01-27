package models.helpers;

public interface ModelInterface {
	boolean needAuthentication();

	void onCreate();

	void onUpdate();
}
