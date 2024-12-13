package it.giga.wastegone.gestioneSmaltimentoRifiuti;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import it.giga.wastegone.R;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.dataAccess.FirebaseRifiutoDAO;
import it.giga.wastegone.gestioneSmaltimentoRifiuti.storage.entity.Rifiuto;
import java.time.DayOfWeek;
import java.util.Collections;
import java.util.Comparator;


/**
 * Activity per il recupero dei rifiuti.
 */

public class RecuperoRifiutoActivity extends AppCompatActivity {

  TextView tvValue;
  Button btColoreRifiuto1;
  Button btColoreRifiuto2;
  Button btColoreRifiuto3;
  Button btColoreRifiuto4;
  Button btColoreRifiuto5;
  Button btColoreRifiuto6;
  Button btGiornoRifiuto1;
  Button btGiornoRifiuto2;
  Button btGiornoRifiuto3;
  Button btGiornoRifiuto4;
  Button btGiornoRifiuto5;
  Button btGiornoRifiuto6;
  Button btGiornoNonConferimento;
  Button btColoreNonUtilizzato;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_recupero_rifiuto);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    tvValue = findViewById(R.id.tvValue);
    btColoreRifiuto1 = findViewById(R.id.btColoreRifiuto1);
    btColoreRifiuto2 = findViewById(R.id.btColoreRifiuto2);
    btColoreRifiuto3 = findViewById(R.id.btColoreRifiuto3);
    btColoreRifiuto4 = findViewById(R.id.btColoreRifiuto4);
    btColoreRifiuto5 = findViewById(R.id.btColoreRifiuto5);
    btColoreRifiuto6 = findViewById(R.id.btColoreRifiuto6);
    btGiornoRifiuto1 = findViewById(R.id.btGiornoRifiuto1);
    btGiornoRifiuto2 = findViewById(R.id.btGiornoRifiuto2);
    btGiornoRifiuto3 = findViewById(R.id.btGiornoRifiuto3);
    btGiornoRifiuto4 = findViewById(R.id.btGiornoRifiuto4);
    btGiornoRifiuto5 = findViewById(R.id.btGiornoRifiuto5);
    btGiornoRifiuto6 = findViewById(R.id.btGiornoRifiuto6);
    btGiornoNonConferimento = findViewById(R.id.btGiornoNonConferimento);
    btColoreNonUtilizzato = findViewById(R.id.btColoreNonUtilizzato);

    FirebaseRifiutoDAO firebaseRifiutoDAO = new FirebaseRifiutoDAO();

    btColoreRifiuto1.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            firebaseRifiutoDAO.doRetrieveRifiutoByColore("Rosso")
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                          @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                              if (task.getResult().isEmpty()) {
                                tvValue.setText("INESISTENTE");
                              } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    try {
                                        tvValue.setText("" + document.get("categoria"));
                                    } catch (Exception e) {
                                        Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                                    }
                                }
                              }
                            } else {
                              Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                            }
                          }
                        });
          }
        });


    btColoreRifiuto2.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            firebaseRifiutoDAO.doRetrieveRifiutoByColore("Giallo")
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("INESISTENTE");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btColoreRifiuto3.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            firebaseRifiutoDAO.doRetrieveRifiutoByColore("Blu")
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("INESISTENTE");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btColoreRifiuto4.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
            firebaseRifiutoDAO.doRetrieveRifiutoByColore("Verde")
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                      @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("INESISTENTE");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                  });
          }
        });

    btColoreRifiuto5.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            firebaseRifiutoDAO.doRetrieveRifiutoByColore("Viola")
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                  @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("INESISTENTE");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                           "Errore nel parsing del documento: ", e);
                              }
                            }
                        }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                    });
          }
        });

    btColoreRifiuto6.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            firebaseRifiutoDAO.doRetrieveRifiutoByColore("Arancione")
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("INESISTENTE");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btColoreNonUtilizzato.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            firebaseRifiutoDAO.doRetrieveRifiutoByColore("Rosa")
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                      @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                tvValue.setText("NON UTILIZZATO");
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                  try {
                                    tvValue.setText("" + document.get("categoria"));
                                  } catch (Exception e) {
                                    Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                                  }
                                }
                            }
                        } else {
                            Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                      }
                    });
          }
        });



    btGiornoRifiuto1.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            DayOfWeek giornoConferimento = DayOfWeek.valueOf("MONDAY");
            firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("NON VALIDO");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                            }
                          }
                        }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });

          }
        });

    btGiornoRifiuto2.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            DayOfWeek giornoConferimento = DayOfWeek.valueOf("TUESDAY");
            firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                      if (task.isSuccessful()) {
                        if (task.getResult().isEmpty()) {
                          tvValue.setText("NON VALIDO");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btGiornoRifiuto3.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            DayOfWeek giornoConferimento = DayOfWeek.valueOf("WEDNESDAY");
            firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("NON VALIDO");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                        }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btGiornoRifiuto4.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            DayOfWeek giornoConferimento = DayOfWeek.valueOf("THURSDAY");
            firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("NON VALIDO");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                        "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btGiornoRifiuto5.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            DayOfWeek giornoConferimento = DayOfWeek.valueOf("FRIDAY");
            firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("NON VALIDO");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btGiornoRifiuto6.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            DayOfWeek giornoConferimento = DayOfWeek.valueOf("SATURDAY");
            firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("NON VALIDO");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });

    btGiornoNonConferimento.setOnClickListener(new View.OnClickListener() {
      @Override
            public void onClick(View view) {
            DayOfWeek giornoConferimento = DayOfWeek.valueOf("SUNDAY");
            firebaseRifiutoDAO.doRetrieveAllRifiutiByDayOfWeek(giornoConferimento)
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                  @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                          if (task.getResult().isEmpty()) {
                            tvValue.setText("NON CONFERIMENTO");
                          } else {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              try {
                                tvValue.setText("" + document.get("categoria"));
                              } catch (Exception e) {
                                Log.e("FirestoreError",
                                                "Errore nel parsing del documento: ", e);
                              }
                            }
                          }
                        } else {
                          Log.e("FirestoreError",
                                    "Errore nel caricamento dei dati: ", task.getException());
                        }
                    }
                });
          }
        });
  }
}