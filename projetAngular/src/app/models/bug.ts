import { Developpeur } from '../models/developpeur';
import { Commentaire } from '../models/commentaire';

export interface Bug {
    idBug?: number;
    titre: string;
    description: string;
    priorite: string;
    avancement: string;
    dateCreation: Date;
    developpeur: Developpeur;
    commentaire: Commentaire[];
  }