#!/usr/bin/python3

import os
import re

IGNORE = ['rename.py', 'README.md', '.git', '.gitignore']

for it in [f for f in os.listdir() if f not in IGNORE]:
    novo_nome = re.sub(r'(?P<letra>[A-Z])', lambda x: f'_{x.group("letra")}'.lower(), it)
    novo_nome = novo_nome if novo_nome[0] != '_' else novo_nome[1:]
    os.rename(it, novo_nome)
