package com.github.elhamjamshidpey.africanBoardGame.view.vaadin;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.elhamjamshidpey.africanBoardGame.Game;
import com.github.elhamjamshidpey.africanBoardGame.component.Player;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "login")
public class VaadinLoginView extends UI implements ViewDisplay {

	@Autowired
	private Game game;

	private Panel springViewDisplay;

	@Override
	public void showView(View view) {
		springViewDisplay.setContent((Component) view);

	}

	@Override
	protected void init(VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();
		setContent(layout);

		TextField firstPlayerName = new TextField("First Player Name:");
		TextField secondPlayerName = new TextField("Second Player Name:");

		layout.addComponent(firstPlayerName);
		layout.addComponent(secondPlayerName);

		Button playButton = new Button("LOGIN");

		playButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				try {
					game.addPlayer(new Player(firstPlayerName.getValue()));
					game.addPlayer(new Player(secondPlayerName.getValue()));
					getPage().setLocation("game");
				} catch (IllegalStateException e) {
					Notification.show("Login Failed!", e.getMessage(), Notification.Type.HUMANIZED_MESSAGE);
				}
			}
		});
		layout.addComponent(playButton);

	}

}
