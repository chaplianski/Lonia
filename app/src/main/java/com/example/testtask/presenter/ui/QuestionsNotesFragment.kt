package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.testtask.R
import com.example.testtask.domain.model.Notes
import com.example.testtask.domain.model.Questions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class QuestionsNotesFragment : Fragment() {

    private val fragList = listOf(
        QuestionsFragment.getInstance(),
        NotesFragment.getInstance()
    )

    private val  titleList = listOf(
        "Questions",
        "Notes"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_questions_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tableLayout: TabLayout = view.findViewById(R.id.tl_questions_notes_fragment)
        val pager: ViewPager2 = view.findViewById(R.id.vp_questions_notes_fragment)
    //    val beginPosition = arguments?.getInt("note", 0)


        pager.adapter =QuestionsNotesAdapter(this, fragList)

        TabLayoutMediator(tableLayout, pager){
                tab, position ->
                tab.text = titleList[position]
        }.attach()




    }

    class QuestionsNotesAdapter (fragment: Fragment, private val list: List<Fragment>): FragmentStateAdapter(fragment){


        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
         //   Log.d("My Log", "position: ${beginPosition}")

            return list[position]

        }


    }

}