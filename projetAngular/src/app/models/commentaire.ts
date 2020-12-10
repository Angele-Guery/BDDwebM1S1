import { Bug } from './bug';

import { Developpeur } from '../models/developpeur';


export interface Commentaire {
    idCom?: number;
    message: string;
    auteur: Developpeur;
    date: Date;
    bug: Bug;
  }