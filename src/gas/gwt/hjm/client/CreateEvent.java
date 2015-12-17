
package gas.gwt.hjm.client;
import gas.gwt.hjm.server.src.Model.Event;
import gas.gwt.hjm.server.src.Model.EventType;
import gas.gwt.hjm.server.src.Model.SchDay;
import java.util.Date;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;

/**
 * @author eironi
 *
 */
public class CreateEvent extends DialogBox {
	private DialogBoxOpener opener = null;

	private static CreateEventUiBinder uiBinder = GWT
			.create(CreateEventUiBinder.class);
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);


	interface CreateEventUiBinder extends UiBinder<Widget, CreateEvent> {
	}

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public CreateEvent(Event event) {
		setWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button create;
	@UiField
	Button cancel;

	@UiField
	DateBox date;
	@UiField
	DateBox time;
	@UiField
	TextBox name;
	@UiField
	TextBox stat;
	//	eventType
	@UiField
	DateBox windowStart;
	@UiField
	DateBox windowDur;
	@UiField
	TextBox windowBrk;
	@UiField
	TextBox windowPos;
	@UiField
	DateBox windowLen;
	@UiField
	TextBox fileName;
	@UiField
	TextBox fileZone;
	@UiField
	TextBox fileNetWork;
	@UiField
	TextBox fileDate;

	@Override
	public String getText() {
		return create.getText();
	}

	@Override
	public void setText(String text) {
		create.setText(text);
	}

	@UiHandler("cancel")
	void onCancelClick(ClickEvent e) {
		CreateEvent.this.hide();
		if (opener != null)
			opener.dialogBoxCancelled();
	}

	@UiHandler("create")
	void onClick(ClickEvent e) {
		Date date1 = null, time1 = null, start1 = null, dur1 = null, length1 = null;
		int brk1 = 0, pos1 = 0;
		String adName1 = null;

		//		EventType eventType;

		String fileName1 = null, fileDate1 = null, fileZone1 = null, fileNetwork1 = null;

		date1 = date.getValue();
		time1 = time.getValue();
		start1 = windowStart.getValue();
		dur1 = windowDur.getValue();
		length1 = windowLen.getValue();
		brk1 = new Integer(windowBrk.getValue());
		pos1 = new Integer(windowPos.getValue());
		adName1 = name.getValue();
		//		eventType = EventType.valueOf(EventType.getNameByValue(tbEventTypeField.getItemText(tbEventTypeField.getSelectedIndex())));

		fileName1 = fileName.getValue();
		fileDate1 = fileDate.getValue();
		fileZone1 = fileZone.getValue();
		fileNetwork1 = fileNetWork.getValue();

		Event myEvent = new Event(date1, time1, start1, dur1, brk1, pos1, length1, adName1, EventType.SCHEDULED);
		final SchDay si = new SchDay(fileName1, fileDate1, fileZone1, fileNetwork1);

		greetingService.createEvent(myEvent, si,
				new AsyncCallback<Event>() {

			@Override
			public void onSuccess(Event result) {
				Window.alert("Success!");
				CreateEvent.this.hide();

				if (opener != null)
					opener.dialogBoxValidated(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed :(");
			}
		});
	}

	public void showDialogBox (final DialogBoxOpener opener) {
		this.opener = opener;
		center();
	}
}