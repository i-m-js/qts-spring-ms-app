import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, Observable } from 'rxjs';
import { Quote } from '../models/quote';
import { QuoteAgg } from '../models/quote-agg';
import { QuoteStat } from '../models/quote-stat';

@Injectable({
  providedIn: 'root'
})
export class QuoteService {

  constructor(private http: HttpClient) { }

  getRandomQuote(): Observable<Quote> {
    return this.http.get<Quote>('/api/quote/random')
    // return new Observable(observer => {
    //   observer.next({ author: 'JS', content: 'This is a test QUote', id: 1, postedBy: 'Js', postedOn: new Date() });
    // })
  }
  getQuote(id: number): Observable<Quote> {
    return this.http.get<Quote>(`/api/quote/${id}`)
  }

  likeQuote(id: number) {
    return this.http.post(`/api/quote/${id}/like`, {});
  }

  removeQuotelike(id: number) {
    return this.http.delete(`/api/quote/${id}/like`, {});
  }

  dislikeQuote(id: number) {
    return this.http.post(`/api/quote/${id}/dislike`, {});
  }

  removeQuoteDislike(id: number) {
    return this.http.delete(`/api/quote/${id}/dislike`, {});
  }

  getQuoteStat(id: number) {
    return this.http.get<QuoteStat>(`/api/quote/${id}/stats`)
  }

}
