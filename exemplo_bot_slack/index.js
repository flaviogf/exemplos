//dependencias
const cron = require('node-cron');
const axios = require('axios');
const Bots = require('./bots');

//declarações
const URL = 'https://hooks.slack.com/services/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';
const obj = new Bots();

obj.find()
    .then(documentos => {
        
        documentos.map(documento => {
            const bot = documento.payload;
            const { 
                segundos,
                minutos,
                horas,
                diaDoMes,
                mes,
                diaDaSemana
            } = documento.horario;

            cron.schedule(`${segundos} ${minutos} ${horas} ${diaDoMes} ${mes} ${diaDaSemana}`, () => {
                axios.post(URL, bot)
                    .then(() => { return })
                    .catch(err => console.log(err.code));
            });

        });

    })
    .catch(() => console.log('ocorreu um erro'));
