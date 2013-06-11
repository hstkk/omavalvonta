PANDOC = pandoc \
        --smart \
        --number-sections \
        --variable geometry:"a4paper, top=1cm, bottom=1cm, left=2cm, right=1cm" \
        --variable mainfont="Times New Roman" \
        --variable sansfont=Arial \
        --variable monofont="Bitstream Vera Sans Mono" \
        --variable fontsize=12pt

all: pdf

pdf:
	$(PANDOC) \
	--output yllapito.pdf \
	yllapito.md
	$(PANDOC) \
	--output kaytto.pdf \
	kaytto.md

