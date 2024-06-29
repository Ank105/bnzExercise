import { NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { FeedbackService } from '../service/feedback.service';
import { response } from 'express';
import { Feedback } from '../dto/feedback.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,FormsModule,NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  
  feedback : Feedback = new Feedback("test@test.com","test messgae","Moderately unhappy");
  
  constructor(private http: HttpClient,private feedbackService: FeedbackService) { 
  }

  submitFeedback() {
    //call servcie
    console.log("clicked");
    this.feedbackService?.submitFeedback(this.feedback).subscribe(
      response => {
        console.log("Feedback saved Successfully",response);
      }
    );
  }
}