import { Bug } from '../models/bug';
import { Commentaire } from '../models/commentaire';

export interface Developpeur {
    idDev?: number;
    nom: string;
    avatar: string;
    commentaire: Commentaire[];
    bug: Bug[];
  }