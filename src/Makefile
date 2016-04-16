###########################
#
# Makefile for Simulation
#
# Voong Kwan 
# Ruimy Benjamin
# Rattanatray Ayutaya
# Ibakuyumcu Arnaud
#
# 11 Avril 2016
#
##########################

# Definition des variables

CLASS=Controleur/Controler \
      Modele/Actions/ListeDActionDetenus \
      Modele/Actions/ListeDActionSurveilles \
      Modele/Actions/Action \
      Modele/Actions/ListeDAction \
      Modele/Marche \
      Modele/Utilisateur \
      Utilitaires/DataBase/DataBase \
      Utilitaires/PontBrownien \
      Vue/Bouton \
      Vue/DialogBoxAsk/DialogBox \
      Vue/DialogBoxAsk/DialogBoxAV \
      Vue/DialogBoxAsk/DialogBoxSurv \
      Vue/FenetreGUI \
      Vue/FinalCashFrame

MAIN=Jeu

SRC_DIR=.
BIN_DIR=../bin
LIB_DIR=../lib

##########################

FILE_OUT= $(CLASS:%=$(BIN_DIR)/%.class)\
	  $(MAIN:%=$(BIN_DIR)/%.class)

##########################

all:$(FILE_OUT)

$(BIN_DIR)/%.class:$(SRC_DIR)/%.java
	javac -d $(BIN_DIR) -classpath $(SRC_DIR):$(LIB_DIR)/ojdbc7.jar $<

play:all
	java -classpath $(LIB_DIR)/ojdbc7.jar:$(BIN_DIR) $(MAIN)

clean:
	rm -rvf $(BIN_DIR)/*
