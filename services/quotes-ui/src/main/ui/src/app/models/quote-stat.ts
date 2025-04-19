import { QuoteAgg } from "./quote-agg";
import { QuoteUserStat } from "./quote-user-stat";

export interface QuoteStat {
    id: number;
    aggStat: QuoteAgg;
    userStat: QuoteUserStat;
}
