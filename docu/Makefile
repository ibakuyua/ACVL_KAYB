###########################
#
# Makefile for TP ACVL
#
# Compilation of documentation
#
# Ibakuyumcu Arnaud
# Voong Kwan
# Rattanatray Ayutaya
# Ruimy Benjamin
#
# 11 Avril 2016
#
############################

DOCU_DIR=.
OPEN=firefox

all:
	doxygen $(DOCU_DIR)/Doxyfile

doc:all
	$(OPEN) $(DOCU_DIR)/html/index.html

clean:
	rm -rfv $(DOCU_DIR)/html/
