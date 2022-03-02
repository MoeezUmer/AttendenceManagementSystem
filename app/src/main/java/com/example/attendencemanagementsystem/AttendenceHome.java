package com.example.attendencemanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttendenceHome extends AppCompatActivity {

    EditText txtname,txtattendence,txtdate;
    Button btnmark,btnview ,btncheck;

    CircleImageView image;
    FloatingActionButton floatbbutton;

    FirebaseFirestore fstore;
    StorageReference storageReference;
    FirebaseAuth fauth;
    StorageReference profileref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence_home);

        txtname=findViewById(R.id.name);
        txtattendence=findViewById(R.id.attendence);
        txtdate=findViewById(R.id.date);
        fstore=FirebaseFirestore.getInstance();
        fauth=FirebaseAuth.getInstance();
        btncheck=findViewById(R.id.leaveacceptreject);
        storageReference= FirebaseStorage.getInstance().getReference();

        StorageReference profileref = storageReference.child("users/"+fauth.getCurrentUser().getUid()+"Profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).into(image);
            }
        });


        image=findViewById(R.id.imageView);
        floatbbutton=findViewById(R.id.floating);

        btnmark=findViewById(R.id.markattendence);
        btnview=findViewById(R.id.viewstudent);


       btnmark.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               String name=txtname.getText().toString();
               String date= txtdate.getText().toString();
               String att= txtattendence.getText().toString();
               String im = image.toString();

               if (TextUtils.isEmpty(name)){
                   txtname.setText("Name is Required");
               }

               if (TextUtils.isEmpty(date)){
                   txtdate.setText("Date is Required");
               }

               if (TextUtils.isEmpty(att)){
                   txtattendence.setText("Attendence is Required");
               }



               Map<String,Object> attend = new HashMap<>();
               attend.put("Name",name);
               attend.put("Date",date);
               attend.put("Attendence",att);
               attend.put("ImageUrl",im);



               fstore.collection("Attendence").add(attend).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                   @Override
                   public void onComplete(@NonNull  Task<DocumentReference> task) {
                       if (task.isSuccessful()){

                           Toast.makeText(AttendenceHome.this,"Attendence Save",Toast.LENGTH_LONG).show();




                       }
                       else {
                           Toast.makeText(AttendenceHome.this, "Error." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }

                   }
               });
           }
       });


       floatbbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               ImagePicker.Companion.with(AttendenceHome.this)
                       .galleryOnly()
                       .cropSquare()
                       .crop()	    			//Crop image(Optional), Check Customization for more option
                       .compress(1024)			//Final image size will be less than 1 MB(Optional)
                       .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                       .start();

           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        //image.setImageURI(uri);

        uploadImageToFirebase(uri);
    }

    private void uploadImageToFirebase(Uri uri) {

        StorageReference fileref= storageReference.child("users/"+fauth.getCurrentUser().getUid()+"Profile.jpg");
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

               fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {

                       Picasso.get().load(uri).into(image);

                   }
               });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AttendenceHome.this,"Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void View(View view){

        startActivity(new Intent(getApplicationContext(),EmployeeData.class));
    }

    public void LeaveRequest(View view){

        startActivity(new Intent(getApplicationContext(),LeaveRequest.class));
    }

    public void LeaveRequestCheck(View view){

        startActivity(new Intent(getApplicationContext(),LeaveAcceptReject.class));

    }
}
