import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, inject, OnDestroy, OnInit } from '@angular/core';
import { Quote } from '../../models/quote';
import { QuoteService } from '../../services/quote.service';

import { ActivatedRoute, Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { Subscription } from 'rxjs';
import { QuoteStat } from '../../models/quote-stat';
import { ShortNumber } from '../../pipes/short-number.pipe';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-dashboard',
  imports: [CardModule, ButtonModule, ShortNumber],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy {
  quote: Quote | undefined | null;
  quoteStat: QuoteStat | undefined | null;
  isLoggedIn = false;
  isLoggingIn = false;
  authServiceLoginSubscription: Subscription | undefined;
  authServiceLoggingSubscription: Subscription | undefined;
  router = inject(Router);
  route = inject(ActivatedRoute)
  // changeDetectionRef = inject(ChangeDetectorRef);

  constructor(private quoteService: QuoteService, private authService: AuthService, private activatedRoute: ActivatedRoute) {
    // this.quote = this.getDefaultQuote();
    // this.quoteStat = this.getDefaultQuoteStat();;
  }


  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.queryParamMap.get('id');
    if (id) {
      this.nextQuote(Number(id));
    } else {
      this.nextRandomQuote();
    }
    this.authServiceLoginSubscription = this.authService.isLoggedIn.subscribe((val) => {
      this.isLoggedIn = val;
      val && this.refreshQuoteStat();
    });
    this.authServiceLoggingSubscription = this.authService.isLoggingIn.subscribe((val) => this.isLoggingIn = val);
    this.isLoggedIn = this.authService.isLoggedIn.getValue();
    this.isLoggingIn = this.authService.isLoggingIn.getValue();

    this.route.queryParams.subscribe(params => {
      params['id'] && this.nextQuote(params['id'])
      // this.changeDetectionRef.markForCheck();
    });
  }
  ngOnDestroy(): void {
    this.authServiceLoginSubscription?.unsubscribe();
    this.authServiceLoggingSubscription?.unsubscribe();
  }

  updateQuoteLike(increment: boolean) {
    if (this.quoteStat) {
      this.quoteStat.aggStat.likes += increment ? 1 : -1;
      if (this.quoteStat.userStat.isDisliked) {
        this.quoteStat.aggStat.dislikes -= 1;
      }
      this.quoteStat.userStat.isDisliked = false;
      this.quoteStat.userStat.isLiked = increment;
    }
  }
  updateQuoteDisLike(increment: boolean) {
    if (this.quoteStat) {
      this.quoteStat.aggStat.dislikes += increment ? 1 : -1;
      if (this.quoteStat.userStat.isLiked) {
        this.quoteStat.aggStat.likes -= 1;
      }
      this.quoteStat.userStat.isLiked = false;
      this.quoteStat.userStat.isDisliked = increment;
    }
  }

  likeQuote() {
    if (this.quote) {
      if (this.quoteStat?.userStat.isLiked) {
        this.quoteService.removeQuotelike(this.quote.id).subscribe(() => {
          this.updateQuoteLike(false);
          this.refreshQuoteStat();
        });
      } else {
        this.quoteService.likeQuote(this.quote.id).subscribe(() => {
          this.updateQuoteLike(true);
          this.refreshQuoteStat();
        })
      }
    }

  }
  dislikeQuote() {
    if (this.quote) {
      if (this.quoteStat?.userStat.isDisliked) {
        this.quoteService.removeQuoteDislike(this.quote.id).subscribe(() => {
          this.updateQuoteDisLike(false);
          this.refreshQuoteStat();
        });
      } else {
        this.quoteService.dislikeQuote(this.quote.id).subscribe(() => {
          this.updateQuoteDisLike(true);
          this.refreshQuoteStat();
        })
      }
    }
  }

  nextQuote(id: number) {
    this.quoteService.getQuote(id).subscribe(quote => this.updateQuoteData(quote));
  }

  nextRandomQuote() {
    this.quoteService.getRandomQuote().subscribe(quote => {
      this.updateQuoteData(quote);
    })
  }

  private updateQuoteData(quote: Quote) {
    this.router.navigate([], {
      queryParams: { id: quote.id },
      // queryParamsHandling: ''
    });
    this.quote = quote;
    this.refreshQuoteStat();
  }

  refreshQuoteStat() {
    if (this.quote) {
      this.quoteService.getQuoteStat(this.quote.id).subscribe(stat => this.quoteStat = stat);
    }
  }
  getDefaultQuote() {
    return { author: '', content: '', id: -11, postedBy: '', postedOn: new Date() };
  }

  getDefaultQuoteStat(): QuoteStat {
    return { id: -1, aggStat: { dislikes: 0, likes: 0 }, userStat: { isDisliked: false, isLiked: false } };
  }

  login() {
    this.router.navigate(['auth', 'login']);
  }
  logout() {
    this.authService.logout();
  }
}
