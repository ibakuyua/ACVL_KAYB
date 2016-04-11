################################
#
# Makefile du TP ACVL
#
# Compilation, documentation
# pour le jeu de simulation
# boursière
#
# Ruimy Benjamin
# Voong Kwan
# Rattanatray Ayutaya
# Ibakuyumcu Arnaud
#
# ENSIMAG
#
# 11 Avril 2016
#
################################
#
# Lire le A_Lire.txt pour plus
# d'information
#
################################

## Definition des variables

SRC_DIR=src
BIN_DIR=bin
DOCU_DIR=docu
OPEN=firefox

#################################

all:
	make SRC_DIR=$(SRC_DIR) BIN_DIR=$(BIN_DIR) -f src/Makefile
	#make DOCU_DIR=$(DOCU_DIR) -f docu/Makefile

play:
	make play SRC_DIR=$(SRC_DIR) BIN_DIR=$(BIN_DIR) -f src/Makefile

doc:
	make doc OPEN=$(OPEN) DOCU_DIR=$(DOCU_DIR) -f docu/Makefile

clean:
	make clean BIN_DIR=$(BIN_DIR) -f src/Makefile
	make clean DOCU_DIR=$(DOCU_DIR) -f docu/Makefile


